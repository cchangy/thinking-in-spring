package com.chency.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件处理
 *
 * @author cchangy
 * @date 2023/06/21 07:21
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AsyncEventHandlerDemo.class);

        context.addApplicationListener(new MySpringEventListener());

        context.refresh();

        // 查找 ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;

            ExecutorService taskThreadPool = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("async-task-thread-pool-"));
            // 同步 -> 异步
            simpleApplicationEventMulticaster.setTaskExecutor(taskThreadPool);

            // 事件异常处理
            simpleApplicationEventMulticaster.setErrorHandler(e -> {
                System.out.printf("[%s] 事件处理出现异常 %s \n", Thread.currentThread().getName(), e.getMessage());
            });

            // 添加 ContextClosedEvent 事件处理
            applicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!taskThreadPool.isShutdown()) {
                        taskThreadPool.shutdown();
                    }
                }
            });
        }

        context.addApplicationListener((new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("故意抛出异常");
            }
        }));

        context.publishEvent(new MySpringEvent("async event"));

        context.close();
    }
}
