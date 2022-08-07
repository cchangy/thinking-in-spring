package com.chency.spring.ioc.lookup;

import com.chency.spring.common.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的依赖查找
 *
 * @author chency
 * @date 2022/05/03 07:49
 */
public class TypeSafetyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TypeSafetyLookupDemo.class);
        context.refresh();

        // 1. 演示BeanFactory#getBean方法的安全性（非安全的）
        displayBeanFactoryGetBean(context);
        // 2. 演示ObjectFactory#getObject方法的安全性（非安全的）
        displayObjectFactoryGetBean(context);
        // 3. 演示ObjectProvider#getIfAvailable方法的安全性（安全的）
        displayObjectProviderIfAvailable(context);
        // 4. 演示ObjectProvider#Stream操作的安全性（安全的）
        displayObjectProviderStreamOps(context);
        // 5. 演示ListableBeanFactory#BeansOfType方法的安全性（安全的）
        displayListableBeanFactory(context);

        context.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeanException("displayObjectProviderStreamOps", () -> beanProvider.stream().forEach(System.out::println));
    }

    private static void displayListableBeanFactory(AnnotationConfigApplicationContext context) {
        printBeanException("displayListableBeanFactory", () -> context.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeanException("displayObjectProviderIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext context) {
        ObjectFactory<User> beanProvider = context.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryGetBean", () -> beanProvider.getObject());
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext context) {
        printBeanException("displayBeanFactoryGetBean", () -> context.getBean(User.class));
    }

    private static void printBeanException(String source, Runnable runnable) {
        System.err.println("source from : " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
