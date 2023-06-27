package com.chency.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Enable 模块示例
 *
 * @author cchangy
 * @date 2023/06/26 07:06
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnableModuleDemo.class);

        context.refresh();

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld);

        context.close();
    }
}
