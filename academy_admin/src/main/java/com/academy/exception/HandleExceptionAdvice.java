package com.academy.exception;

import com.academy.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandleExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception exception){
        return Result.error(exception.getMessage());
    }

    /**
     * hibernate参数校验异常统一处理
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result resolveMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        exception.printStackTrace();
        log.debug("系统抛出了异常，异常为：{}", exception.getMessage());
        return Result.error(exception.getBindingResult().getFieldError().getDefaultMessage());
    }
}