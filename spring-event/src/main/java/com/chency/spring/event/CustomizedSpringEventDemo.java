package com.chency.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义 Spring 事件 示例
 *
 * @author cchangy
 * @date 2023/06/21 07:39
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CustomizedSpringEventDemo.class);

        // 添加事件监听
        context.addApplicationListener(new MySpringEventListener());

        context.refresh();

        // 发布事件
        context.publishEvent(new MySpringEvent("spring event"));
        // 子事件也会被MySpringEventListener监听到
        context.publishEvent(new MySpringEventChild("spring event child"));

        context.close();
    }
}
