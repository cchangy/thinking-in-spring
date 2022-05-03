package com.chaytech.spring.bean.definition;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体bean注册示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02 23:10
 */
public class SingletonBeanRegisterDemo {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 创建一个外部对象
        User user = new User();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 注册外部单例对象
        beanFactory.registerSingleton("user", user);
        context.refresh();

        User userByLookup = beanFactory.getBean("user", User.class);
        System.out.println(user == userByLookup);
        context.close();
    }
}
