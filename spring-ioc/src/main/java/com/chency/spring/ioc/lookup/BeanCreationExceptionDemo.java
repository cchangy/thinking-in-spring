package com.chency.spring.ioc.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * BeanCreationException 示例
 *
 * @author chency
 * @date 2022/05/03 08:41
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestClass.class);
        context.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());
        context.refresh();
    }

    public static class TestClass implements InitializingBean {

        @PostConstruct
        public void init() {
            throw new RuntimeException("@PostConstruct");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet");
        }
    }
}
