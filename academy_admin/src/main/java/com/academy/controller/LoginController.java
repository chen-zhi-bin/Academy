package com.academy.controller;

import com.academy.domain.dto.UserLoginDTO;
import com.academy.domain.po.Menu;
import com.academy.domain.po.User;
import com.academy.domain.vo.RouterVo;
import com.academy.entity.Result;
import com.academy.interceptor.AuthenticationContextHolder;
import com.academy.service.IMenuService;
import com.academy.service.IUserService;
import com.academy.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/system")
@Tag(name = "登录")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户点击了登录。。。。。。");
        User user = userService.login(userLoginDTO);
        if(user == null){
            return Result.error("用户名或者密码错误");
        }
        if(user.getIsDisabled().equals("1")){
            return Result.error("该用户被锁定，请联系管理员");
        }
        Map<String, Object> map = new HashMap<>();
        // token:令牌,证明用户已经登录
        // 调用jwt的工具类，生成token
        map.put("username", user.getUsername());
        map.put("mobile", user.getMobile());
        String jwtToken = JwtUtil.createJWT(user.getId(), map, 1000L * 60 * 30);

        // 将生成的token保存到redis中
        stringRedisTemplate.opsForValue().set("token-" + user.getUsername(), jwtToken, 30, TimeUnit.MINUTES);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", jwtToken);
        return Result.ok(resultMap);
    }

    @GetMapping("/info")
    @Operation(summary = "获取登录用户信息")
    public Result<Map<String, Object>> info(HttpServletRequest request) throws Exception{
//        // 1. 从请求头中获取token，此时token就是用户名
//        String authorization = request.getHeader("Authorization");
//        if(StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")){
//            return Result.error("非法的token");
//        }
//        String jwtToken = authorization.substring(7);
//        // 解析jwtToken, 获取载荷中的用户名
//        Claims claims = JwtUtil.parseJWT(jwtToken);
//        String username = claims.get("username").toString();
        String username = AuthenticationContextHolder.getContext();
        // 2. 根据token获取用户的信息
        User user = userService.selectByUsername(username);
        // 3. 根据token获取角色信息
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        // 4. 根据token获取权限信息
        List<String> permissions = new ArrayList<>();
        permissions.add("*:*:*");
        // 5. 返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roles", roles);
        map.put("permissions", permissions);
        return Result.ok(map);
    }

    @Operation(summary = "获取菜单集合")
    @GetMapping("/router")
    public Result<List<RouterVo>> listRouterMenus(HttpServletRequest request) throws Exception {
//        String token = request.getHeader("Authorization");
//        token = request.getHeader("Authorization");
//        if(StringUtils.isEmpty(token) || !token.startsWith("Bearer ")){
//            return Result.error("非法的token");
//        }
//        String jwtToken = token.substring(7);
//        // 解析jwtToken, 获取载荷中的用户名
//        Claims claims = JwtUtil.parseJWT(jwtToken);
//        String username = claims.get("username").toString();


        String username = AuthenticationContextHolder.getContext();
        // 根据用户名获取该用户具备的权限菜单列表
        List<Menu> menus = menuService.listTreeMenusByUsername(username);
        List<RouterVo> routerVos = menuService.buildMenus(menus);
        return Result.ok(routerVos);
    }

}
