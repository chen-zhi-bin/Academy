package com.academy.interceptor;

/**
 * 身份验证码的本地线程对象
 */
public class AuthenticationContextHolder
{
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getContext()
    {
        return contextHolder.get();
    }

    public static void setContext(String context)
    {
        contextHolder.set(context);
    }

    public static void clearContext()
    {
        contextHolder.remove();
    }
}
