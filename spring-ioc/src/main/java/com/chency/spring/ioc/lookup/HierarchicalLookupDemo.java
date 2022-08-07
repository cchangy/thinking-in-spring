package com.chency.spring.ioc.lookup;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 *
 * @author chency
 * @date 2022/05/03 00:01
 */
public class HierarchicalLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        System.out.println("当前beanFactory的 Parent BeanFactory: " + beanFactory.getParentBeanFactory());

        // 2. 设置parent beanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前beanFactory的 Parent BeanFactory: " + beanFactory.getParentBeanFactory());

        displayLocalBean(beanFactory, "user");
        displayLocalBean(parentBeanFactory, "user");

        context.refresh();
        context.close();
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s]是否包含bean [name=%s]: %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentFactory() {
        // 1. 创建ioc容器beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 创建配置读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 3. 加载bean，返回加载bean的数量
        beanDefinitionReader.loadBeanDefinitions("classpath:dependent-lookup-context.xml");
        return beanFactory;
    }
}
