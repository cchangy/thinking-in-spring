package com.chency.spring.ioc.injection.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * aware接口回调依赖注入示例
 *
 * @author chency
 * @date 2022/05/04 09:25
 */
public class AwareInterfaceCallbackInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareInterfaceCallbackInjectionDemo.class);
        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceCallbackInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceCallbackInjectionDemo.applicationContext = applicationContext;
    }
}
