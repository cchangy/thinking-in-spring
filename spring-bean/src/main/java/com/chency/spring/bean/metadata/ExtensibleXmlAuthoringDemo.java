package com.chency.spring.bean.metadata;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * spring xml 元素扩展示例
 *
 * @author cchangy
 * @date 2023/06/04 19:29
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("user-context.xml");
        User user = beanFactory.getBean(User.class);
        System.out.println("User=" + user);
    }
}
