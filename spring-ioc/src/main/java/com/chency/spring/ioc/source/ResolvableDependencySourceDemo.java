package com.chency.spring.ioc.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源示例
 *
 * @author chency
 * @date 2022/05/09 06:53
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ResolvableDependencySourceDemo.class);

        context.addBeanFactoryPostProcessor(beanFactory -> {
            // 只能用于类型的依赖注入
            beanFactory.registerResolvableDependency(String.class, "hello,world");
        });

        context.refresh();
    }
}
