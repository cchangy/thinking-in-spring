package com.chency.spring.aop.pointcut;

import com.chency.spring.aop.aspectj.interceptor.EchoServiceMethodInterceptor;
import com.chency.spring.aop.proxy.DefaultEchoService;
import com.chency.spring.aop.proxy.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * Pointcut api使用示例
 *
 * @author chency
 * @date 2022/08/21 09:58
 */
@Slf4j
public class PointcutApiDemo {

    public static void main(String[] args) {
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        // 组合实现
        ComposablePointcut pointcut = new ComposablePointcut(EchoServiceEchoMethodPointcut.getInstance());
        pointcut.intersection(echoServicePointcut.getClassFilter());
        pointcut.intersection(echoServicePointcut.getMethodMatcher());

        // 将Pointcut适配成advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

        // 指定代理对象
        ProxyFactory proxyFactory = new ProxyFactory(new DefaultEchoService());
        // 添加advisor
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        log.info(echoService.echo("pointcut api"));
    }
}
