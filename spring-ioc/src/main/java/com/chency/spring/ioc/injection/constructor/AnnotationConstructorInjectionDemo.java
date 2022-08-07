package com.chency.spring.ioc.injection.constructor;

import com.chency.spring.common.domain.User;
import com.chency.spring.ioc.injection.UserHolder;
import com.chency.spring.ioc.injection.setter.AnnotationSetterInjectionDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解的constructor方法注入示例
 *
 * @author chency
 * @date 2022/05/03 22:19
 */
public class AnnotationConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationSetterInjectionDemo.class);
        context.refresh();

        UserHolder userHolder = context.getBean(UserHolder.class);
        System.out.println(userHolder);
    }

    @Bean
    public User user() {
        return User.createUser();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
