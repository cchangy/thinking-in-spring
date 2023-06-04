package com.chency.spring.bean.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于XML资源的YAML 外部化配置示例
 *
 * @author cchangy
 * @date 2023/06/04 20:27
 */
public class XmlYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("yaml-property-source-context.xml");
        Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println("yamlMap=" + yamlMap);
    }
}
