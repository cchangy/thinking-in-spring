package com.chency.spring.ioc.injection.annotation;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解的字段注入示例
 *
 * @author chency
 * @date 2022/05/04 08:40
 */
public class AnnotationFieldInjectionDemo {

    @Autowired
    private User userByAutowired;

    @Resource
    private User userByResource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationFieldInjectionDemo.class);
        context.refresh();

        AnnotationFieldInjectionDemo demo = context.getBean(AnnotationFieldInjectionDemo.class);
        System.out.println("userByAutowired: " + demo.userByAutowired);
        System.out.println("userByResource: " + demo.userByResource);
        System.out.println(demo.userByAutowired == demo.userByResource);
    }

    @Bean
    public User user() {
        return User.createUser();
    }
}
