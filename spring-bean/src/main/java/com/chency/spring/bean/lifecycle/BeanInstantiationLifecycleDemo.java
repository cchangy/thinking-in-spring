package com.chency.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean实例化生命周期示例
 *
 * @author chency
 * @date 2022/07/31 16:41
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
//        executeBeanFactory();
        System.out.println("---------------------------");
        executeApplicationContext();
    }

    private static void executeBeanFactory () {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：手动注册MyInstantiationAwareBeanPostProcessor
        // beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二：将MyInstantiationAwareBeanPostProcessor作为bean注册

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanNum = beanDefinitionReader.loadBeanDefinitions("dependent-lookup-context.xml");
        System.out.println("加载的BeanDefinition数量：" + beanNum);

        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory.getBean("vipUser"));
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependent-lookup-context.xml");
        // 刷新应用上下文
        applicationContext.refresh();

        System.out.println(applicationContext.getBean("user"));
        System.out.println(applicationContext.getBean("vipUser"));
        // 关闭应用上下文
        applicationContext.close();
    }
}
