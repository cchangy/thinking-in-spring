package com.chaytech.spring.ioc.injection.lazy;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 延迟依赖注入示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/05 22:00
 */
public class LazyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<User> userObjectFactory; // 延迟注入

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LazyInjectionDemo.class);
        context.refresh();

        LazyInjectionDemo bean = context.getBean(LazyInjectionDemo.class);
        System.out.println("user: " + bean.user);
        System.out.println("userObjectProvider: " + bean.userObjectProvider.getObject());
        System.out.println("userObjectFactory: " + bean.userObjectFactory.getObject());
    }

    @Bean
    public User user() {
        return User.createUser();
    }
}
