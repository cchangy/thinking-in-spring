package com.chency.spring.bean.instantiation;

import com.chency.spring.bean.factory.DefaultUserFactory;
import com.chency.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

/**
 * 特殊bean实例化示例
 *
 * @author chency
 * @date 2022/05/02
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = context.getBeanFactory();

        // 1. 通过ServiceLoaderFactoryBean创建bean
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);

        // 2.通过AutowireCapableBeanFactory创建bean
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());

        // serviceLoaderDemo();
    }

    public static void serviceLoaderDemo() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        serviceLoader.forEach(e -> {
            System.out.println(e.createUser());
        });
    }
}
