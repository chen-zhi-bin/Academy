package com.academy.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共的响应对象
 * @author tarnett
 */
@Data
@Tag(name = "统一响应对象")
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    @Schema(description = "响应状态码")
    private Integer code;
    @Schema(description = "响应消息")
    private String message;
    @Schema(description = "响应数据")
    private T data;

    private static <T> Result<T> common(int code, String message, T data){
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> commonByRow(int row){
        if(row > 0){
            return ok();
        }else{
            return error();
        }
    }

    public static <T> Result<T> commonByFlag(Boolean flag){
        if(flag){
            return ok();
        }else{
            return error();
        }
    }

    public static <T> Result<T> ok(){
        return common(20000, "操作成功",null);
    }
    public static <T> Result<T> ok(String message){
        return common(20000, message, null);
    }
    public static <T> Result<T> ok(T data){
        return common(20000, "数据查询成功", data);
    }
    public static <T> Result<T> ok(int code, String message, T data){
        return common(code, message, data);
    }

    public static <T> Result<T> error(){
        return common(50000, "操作失败",null);
    }
    public static <T> Result<T> error(String message){
        return common(50000, message,null);
    }
    public static <T> Result<T> error(int code, String message){
        return common(code, message,null);
    }
    public static <T> Result<T> error(int code, String message, T data){
        return common(code, message, data);
    }
}