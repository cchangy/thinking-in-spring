package com.chency.spring.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 异常拦截器
 *
 * @author chency
 * @date 2022/08/20 17:12
 */
public interface ExceptionInterceptor {

    /**
     * 异常执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param throwable
     */
    void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
}
