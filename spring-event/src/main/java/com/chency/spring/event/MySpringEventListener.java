package com.chency.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * 自定义 Spring 事件监听
 *
 * @author cchangy
 * @date 2023/06/21 07:22
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[%s] MySpringEventListener on event %s \n", Thread.currentThread().getName(), event);
    }
}
