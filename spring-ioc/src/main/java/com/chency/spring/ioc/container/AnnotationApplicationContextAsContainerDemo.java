package com.chency.spring.ioc.container;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解能力的ApplicationContext作为ioc容器
 *
 * @author chency
 * @Date 2022/05/02
 */
public class AnnotationApplicationContextAsContainerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类注册进注解支持的applicationContext
        applicationContext.register(AnnotationApplicationContextAsContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 查找bean对象
        lookupListByType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setAge(18);
        user.setName("chency");
        return user;
    }

    private static void lookupListByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("查找到的所有user对象：" + userMap);
        }
    }
}
