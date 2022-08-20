package com.chency.spring.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @author chency
 * @date 2022/08/20 16:47
 */
public interface BeforeInterceptor {

    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
