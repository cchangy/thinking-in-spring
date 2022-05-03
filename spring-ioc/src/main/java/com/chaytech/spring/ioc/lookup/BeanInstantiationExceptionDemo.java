package com.chaytech.spring.ioc.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanInstantiationException 示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/03 08:41
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        context.registerBeanDefinition("errorBean",beanDefinitionBuilder.getBeanDefinition());
        context.refresh();

    }
}
