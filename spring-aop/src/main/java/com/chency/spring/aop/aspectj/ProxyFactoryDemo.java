package com.chency.spring.aop.aspectj;

import com.chency.spring.aop.aspectj.interceptor.EchoServiceMethodInterceptor;
import com.chency.spring.aop.proxy.DefaultEchoService;
import com.chency.spring.aop.proxy.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;

/**
 * proxy factory
 *
 * @author chency
 * @date 2022/08/21 09:12
 */
@Slf4j
public class ProxyFactoryDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 指定目标代理对象
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

        // 添加advice，MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        log.info(echoService.echo("proxy factory"));
    }
}
