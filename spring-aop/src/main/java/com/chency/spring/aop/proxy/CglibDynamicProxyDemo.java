package com.chency.spring.aop.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理示例
 *
 * @author chency
 * @date 2022/08/20 19:20
 */
@Slf4j
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 指定拦截的类
        enhancer.setSuperclass(DefaultEchoService.class);
        // 指定回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.info("echo start...");
                /**
                 * source -> cglib子类
                 * 目标类 -> DefaultEchoService
                 *
                 * 错误使用：method.invoke(source, args);
                 */
                Object result = methodProxy.invokeSuper(source, args);
                log.info("echo end...");
                return result;
            }
        });

        // 创建代理对象
        EchoService echoService = (EchoService) enhancer.create();
        // 执行调用
        log.info(echoService.echo("cglib dynamic proxy"));
    }
}
