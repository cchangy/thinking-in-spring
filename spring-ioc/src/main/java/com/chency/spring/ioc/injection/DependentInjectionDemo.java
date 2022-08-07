package com.chency.spring.ioc.injection;

import com.chency.spring.ioc.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 *
 * @author chency
 * @Date 2022/05/02
 */
public class DependentInjectionDemo {

    public static void main(String[] args) {
        // 启动spring应用上下文
        // BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependent-injection-context.xml");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:dependent-injection-context.xml");

        // 依赖来源一：自定义bean
        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        System.out.println("users：" + userRepository.getUsers());

        // 依赖来源二：依赖注入，内建依赖
        System.out.println("userRepositoryBeanFactory: " + userRepository.getBeanFactory());
        System.out.println("userRepositoryBeanFactory == beanFactory: " + (userRepository.getBeanFactory() == context));

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == context);

        // 依赖查找错误示例
        // System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建bean
        Environment environment = context.getBean(Environment.class);
        System.out.println("容器内建的bean：" + environment);

        whoIsIocContainer(userRepository, context);
    }


    /**
     * 谁是ioc容器？
     *
     * @param userRepository
     * @param context
     */
    private static void whoIsIocContainer(UserRepository userRepository, AbstractApplicationContext context) {
        // 为什么不相等？applicationContext和beanFactory是组合关系，是不同的对象，获取真正底层的beanFactory
        System.out.println(userRepository.getBeanFactory() == context);
        System.out.println(userRepository.getBeanFactory() == context.getBeanFactory());
    }
}
