package com.chaytech.spring.ioc.lookup;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过ObjectProvider进行依赖查找
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02 23:42
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);
        context.refresh();

        lookupByObjectProvider(context);
        lookupIfAvailable(context);
        lookupByStreamOps(context);

        context.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        beanProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

    @Bean
    @Primary
    public String hello() {
        return "hello";
    }

    @Bean
    public String message() {
        return "message";
    }
}
