package com.chaytech.spring.bean.instantiation;

import com.chaytech.spring.common.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 常规bean实例化示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean-instantiation-context.xml");

        User user = context.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = context.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = context.getBean("user-by-factory-bean", User.class);

        System.out.println(user == userByInstanceMethod);
        System.out.println(user == userByFactoryBean);
    }
}
