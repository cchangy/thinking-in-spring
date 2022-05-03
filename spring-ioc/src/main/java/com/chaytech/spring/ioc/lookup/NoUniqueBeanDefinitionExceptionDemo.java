package com.chaytech.spring.ioc.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * NoUniqueBeanDefinitionException 示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/03 08:35
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NoUniqueBeanDefinitionExceptionDemo.class);
        context.refresh();

        try {
            context.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring上下文存在%s个%s类型的bean,具体原因: %s", e.getNumberOfBeansFound(), e.getBeanType().getName(), e.getMessage());
        }
    }


    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }
}
