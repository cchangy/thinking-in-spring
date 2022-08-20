package com.chency.spring.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 最终拦截器
 *
 * @author chency
 * @date 2022/08/20 16:47
 */
public interface FinallyInterceptor {

    /**
     * 最终执行
     * 
     * @param proxy
     * @param method
     * @param args
     * @param result 目标方法返回结果
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object result);
}
