package com.chency.spring.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 后置拦截器
 *
 * @author chency
 * @date 2022/08/20 16:47
 */
public interface AfterInterceptor {

    /**
     * 后置执行
     * 
     * @param proxy
     * @param method
     * @param args
     * @param result 目标方法返回结果
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object result);
}
