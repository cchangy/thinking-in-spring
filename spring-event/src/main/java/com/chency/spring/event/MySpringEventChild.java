package com.chency.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 *
 * @author cchangy
 * @date 2023/06/21 07:21
 */
public class MySpringEventChild extends MySpringEvent {

    public MySpringEventChild(String source) {
        super(source);
    }
}
