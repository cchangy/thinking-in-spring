package com.chency.spring.ioc.injection.annotation;

import com.chency.spring.common.domain.User;
import com.chency.spring.ioc.injection.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解的方法注入示例
 *
 * @author chency
 * @date 2022/05/04 08:40
 */
public class AnnotationMethodInjectionDemo {

    private UserHolder userHolderByAutowired;
    private UserHolder userHolderByResource;

    @Autowired
    public void setUserHolderByAutowired(UserHolder userHolderByAutowired) {
        this.userHolderByAutowired = userHolderByAutowired;
    }

    @Resource
    public void setUserHolderByResource(UserHolder userHolderByResource) {
        this.userHolderByResource = userHolderByResource;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationMethodInjectionDemo.class);
        context.refresh();

        AnnotationMethodInjectionDemo demo = context.getBean(AnnotationMethodInjectionDemo.class);
        System.out.println("userByAutowired: " + demo.userHolderByAutowired);
        System.out.println("userByResource: " + demo.userHolderByResource);
        System.out.println(demo.userHolderByAutowired == demo.userHolderByResource);
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
