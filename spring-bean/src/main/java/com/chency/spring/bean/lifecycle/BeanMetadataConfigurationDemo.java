package com.chency.spring.bean.lifecycle;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * bean 元信息配置示例
 *
 * @author chency
 * @date 2022/07/31 09:38
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        // 指定字符编码UTF-8
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource("META-INF/user.properties"), "UTF-8");
        // 加载properties资源
        int beanNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);

        System.out.println("加载的BeanDefinition数量：" + beanNum);

        // 通过bean id和类型查找bean
        System.out.println(beanFactory.getBean("user", User.class));
    }
}
