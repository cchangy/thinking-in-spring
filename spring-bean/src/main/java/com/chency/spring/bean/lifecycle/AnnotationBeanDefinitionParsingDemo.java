package com.chency.spring.bean.lifecycle;

import com.chency.spring.bean.definition.AnnotationBeanDefinitionDemo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解BeanDefinition解析示例
 *
 * @author chency
 * @date 2022/07/31 09:59
 */
public class AnnotationBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于注解的BeanDefinitionReader
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        // 注册当前类（非@Component）
        beanDefinitionReader.register(AnnotationBeanDefinitionDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;

        System.out.println("加载的beanDefinition数量:" + beanDefinitionCount);

        AnnotationBeanDefinitionDemo annotationBeanDefinitionDemo = beanFactory.getBean(AnnotationBeanDefinitionDemo.class);
        System.out.println(annotationBeanDefinitionDemo);
    }
}
