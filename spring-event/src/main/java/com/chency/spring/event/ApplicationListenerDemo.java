package com.chency.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * {@link ApplicationListener} 示例
 *
 * @author cchangy
 * @date 2023/06/20 06:45
 */
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);

        // 方法一：面向接口向context注册事件
        context.addApplicationListener(event -> System.out.println("接收到的Spring事件: " + event));

        // 方法二：基于@EventListener注解

        // 方法三：将ApplicationListener注册为bean
        context.register(MyApplicationListener.class);

        context.refresh(); // ContextRefreshedEvent
        context.start(); // ContextStartedEvent
        context.stop(); // ContextStoppedEvent
        context.close(); // ContextClosedEvent
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello,world"){
        });

        // 会发送PayloadApplicationEvent
        applicationEventPublisher.publishEvent("hello,world");
    }

    static class MyApplicationListener implements ApplicationListener {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println("MyApplicationListener onEvent: " + event);
        }
    }

    @EventListener
    public void onEvent(ContextRefreshedEvent event) {
        System.out.println("@EventListener onEvent#ContextRefreshedEvent: " + event);
    }

    @EventListener
    public void onEvent(ContextStartedEvent event) {
        System.out.println("@EventListener onEvent#ContextStartedEvent: " + event);
    }

    @EventListener
    public void onEvent(ContextClosedEvent event) {
        System.out.println("@EventListener onEvent#ContextClosedEvent: " + event);
    }
}
