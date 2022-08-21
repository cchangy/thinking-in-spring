package com.chency.spring.aop.aspectj;

import com.chency.spring.aop.aspectj.aspect.AspectConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * aspectJ api使用示例
 *
 * @author chency
 * @date 2022/08/21 08:44
 */
@Slf4j
public class AspectJAnnotationUsingApiDemo {

    public static void main(String[] args) {
        // 通过创建一个hashMap缓存，作为代理对象
        Map<String, Object> cache = new HashMap<>();
        // 创建 aspectJ代理工厂
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(cache);
        // 增加aspect配置类
        aspectJProxyFactory.addAspect(AspectConfiguration.class);

        // 添加MethodBeforeAdvice
        aspectJProxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                if ("put".equals(method.getName()) && objects.length == 2) {
                    log.info("[BeforeAdvice]当前存放的key是: {}, value: {}", objects[0], objects[1]);
                }
            }
        });

        // 添加AfterReturningAdvice
        aspectJProxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
                if ("put".equals(method.getName()) && objects.length == 2) {
                    log.info("[AfterReturningAdvice]当前存放的key是: {}, value: {}，旧的value: {}", objects[0], objects[1], returnValue);
                }
            }
        });

       Map<String, Object> proxyObject = aspectJProxyFactory.getProxy();
        proxyObject.put("user", "小明");
        proxyObject.put("user", "李四");
    }
}
