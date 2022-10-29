package com.chency.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认实现
 *
 * @author chency
 * @date 2022/05/02
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean, SmartInitializingSingleton {

    // 1. 基于@PostConstruct注解的初始化
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : user factory init...");
    }

    // 2. 自定义的初始化方法
    public void initUserFactory() {
        System.out.println("initUserFactory() : user factory init...");
    }

    // 3. InitializingBean的初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet() : user factory init...");
    }

    // 1. 基于@PreDestroy注解的销毁
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy() : user factory destroy...");
    }

    // 2. 基于DisposableBean的销毁
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy() : user factory destroy...");
    }

    // 3. 自定义的销毁方法
    public void doDestroy() {
        System.out.println("doDestroy() : user factory destroy...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("DefaultUserFactory正在被回收...");
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("afterSingletonsInstantiated()...");
    }
}
