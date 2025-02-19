package com.academy.interceptor;

import com.academy.entity.Result;
import com.academy.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String WHITE_PATH = "/system/login,/system/logout,/trainer/list/all";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 放行白名单
        String uri = request.getRequestURI();
        if(WHITE_PATH.contains(uri)){
            log.debug("请求地址为白名单地址：{}，执行放行操作", uri);
            return true;
        }
        // 2. 判断header中是否存在Authorization请求头
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token) || !token.startsWith("Bearer")){
            log.debug("非法的token，值为：{}", token);
            printResult(response, Result.error(40001,"请求中未包含token"));
            return false;
        }
        // 3. 使用JWT解析token，如果不正确，则表示非法的请求
        try {
            // 没有异常说明token解析正确
            token = token.substring(7);
            Claims claims = JwtUtil.parseJWT(token);
            Object username = claims.get("username");
            log.debug("token解析成功，登录的用户名为：{}", username);

            // 将用户名保存到ThreadLocal中，用于后续的业务获取用户名
            AuthenticationContextHolder.setContext(username.toString());

            // 4. 如果正确则执行放行操作
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            // 抛出异常，说明token解析失败
            printResult(response, Result.error(40001,"非法的token"));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*一定要清除ThreadLocal中的内容，否则可能会造成内存泄露*/
        AuthenticationContextHolder.clearContext();
    }

    /**
     * 打印错误的JSON响应数据
     * @param response
     * @param result
     */
    private void printResult(HttpServletResponse response, Result result){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String messageResult = objectMapper.writeValueAsString(result);
            writer.print(messageResult);
        } catch (Exception e) {
            log.debug("login token error is {}", e.getMessage());
        }
    }
}
