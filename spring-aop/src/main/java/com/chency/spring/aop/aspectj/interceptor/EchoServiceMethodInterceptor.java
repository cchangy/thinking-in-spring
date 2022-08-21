package com.chency.spring.aop.aspectj.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * aop方法拦截器
 *
 * @author chency
 * @date 2022/08/21 09:07
 */
@Slf4j
public class EchoServiceMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        log.info("拦截echoService的方法: {}", method);
        return methodInvocation.proceed();
    }
}
