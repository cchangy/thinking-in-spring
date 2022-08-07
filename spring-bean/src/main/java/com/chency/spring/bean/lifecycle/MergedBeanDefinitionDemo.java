package com.chency.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * beanDefinition合并示例
 *
 * @author chency
 * @date 2022/07/31 10:22
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        int beanNum = beanDefinitionReader.loadBeanDefinitions("dependent-lookup-context.xml");
        System.out.println("加载的BeanDefinition数量：" + beanNum);

        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory.getBean("vipUser"));
    }
}
