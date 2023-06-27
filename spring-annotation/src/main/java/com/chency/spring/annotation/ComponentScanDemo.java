package com.chency.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * {@link Component} 示例
 *
 * @author cchangy
 * @date 2023/06/22 06:32
 */
@ComponentScan(basePackages = {"com.chency.spring.annotation"})
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ComponentScanDemo.class);

        context.refresh();

        // @MyComponent2 <- @MyComponent <- @Component
        // 从 Spring 4.0 开始支持多层次 @Component 派生
        MyComponentUsedClass bean = context.getBean(MyComponentUsedClass.class);
        System.out.println(bean);

        context.close();
    }

}
