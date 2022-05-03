package com.chaytech.spring.ioc.container;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * ioc容器示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
public class IocContainerDemo {

    public static void main(String[] args) {
        // 1. 创建ioc容器beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 创建配置读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 3. 加载bean，返回加载bean的数量
        int loadCount = beanDefinitionReader.loadBeanDefinitions("classpath:dependent-lookup-context.xml");
        System.out.println("加载bean的数量：" + loadCount);

        lookupListByType(beanFactory);
    }

    private static void lookupListByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("查找到的所有user对象：" + userMap);
        }
    }
}
