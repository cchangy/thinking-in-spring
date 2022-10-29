package com.chency.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean初始化生命周期示例
 *
 * @author chency
 * @date 2022/07/31 16:41
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：手动注册MyInitializationAwareBeanPostProcessor
         beanFactory.addBeanPostProcessor(new MyInitializationAwareBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanNum = beanDefinitionReader.loadBeanDefinitions("dependent-lookup-context.xml");
        System.out.println("加载的BeanDefinition数量：" + beanNum);

        System.out.println(beanFactory.getBean("user"));
    }
}
