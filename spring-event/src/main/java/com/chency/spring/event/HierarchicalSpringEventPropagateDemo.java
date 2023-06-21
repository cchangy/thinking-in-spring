package com.chency.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Spring 层次性事件传播 示例
 *
 * @author cchangy
 * @date 2023/06/20 07:12
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 创建 parent Spring 应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyEventListener.class);

        // 创建 current String 应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");

        // current -> parent
        currentContext.setParent(parentContext);
        currentContext.register(MyEventListener.class);

        parentContext.refresh();
        currentContext.refresh();

        currentContext.close();
        parentContext.close();
    }

    static class MyEventListener implements ApplicationListener<ApplicationContextEvent> {

        private static Set<ApplicationContextEvent> processedEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (processedEvents.add(event)) {
                System.out.printf("监听到 Spring 应用下文件[ID : %s] 的 %s 事件\n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
            }
        }
    }
}
