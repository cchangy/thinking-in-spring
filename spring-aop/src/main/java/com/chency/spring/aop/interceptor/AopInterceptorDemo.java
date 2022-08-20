package com.chency.spring.aop.interceptor;

import com.chency.spring.aop.proxy.DefaultEchoService;
import com.chency.spring.aop.proxy.EchoService;
import com.chency.spring.aop.proxy.ProxyEchoService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * aop拦截器示例
 *
 * @author chency
 * @date 2022/08/20 16:44
 */
@Slf4j
public class AopInterceptorDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 判断是否是EchoService以及它的子类
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // 前置拦截器
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };

                    Long startTime = 0L;
                    Long endTime = 0L;
                    Object result = null;
                    try {
                        startTime = (long) beforeInterceptor.before(proxy, method, args);
                        // 调用目标方法
                        ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                        result = echoService.echo((String) args[0]);

                        // 后置拦截器
                        AfterInterceptor afterInterceptor = new AfterInterceptor() {
                            @Override
                            public Object after(Object proxy, Method method, Object[] args, Object result) {
                                return System.currentTimeMillis();
                            }
                        };
                        endTime = (long) afterInterceptor.after(proxy, method, args, result);
                    } catch (Exception e) {
                        // 异常拦截器
                        ExceptionInterceptor exceptionInterceptor = new ExceptionInterceptor() {
                            @Override
                            public void intercept(Object proxy, Method method, Object[] args, Throwable throwable) {
                            }
                        };
                        exceptionInterceptor.intercept(proxy, method, args, e);
                    } finally {
                        Long finalEndTime = endTime;
                        Long finalStartTime = startTime;
                        FinallyInterceptor finallyInterceptor = new FinallyInterceptor() {
                            @Override
                            public Object finalize(Object proxy, Method method, Object[] args, Object result) {
                                return finalEndTime - finalStartTime;
                            }
                        };
                        long costTime = (long) finallyInterceptor.finalize(proxy, method, args, result);
                        log.info("echo方法执行耗时: {}ms", costTime);
                    }
                    return result;
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        log.info(echoService.echo("jdk dynamic proxy"));
    }
}
