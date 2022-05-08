package com.chaytech.spring.ioc.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖来源示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/09 07:03
 */
@Configuration
@PropertySource("classpath:default.properties")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${user.name}")
    private String name;

    @Value("${user.resource:classpath:default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ExternalConfigurationDependencySourceDemo.class);
        context.refresh();

        ExternalConfigurationDependencySourceDemo bean = context.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("user.id: " + bean.id);
        System.out.println("user.name: " + bean.name);
        System.out.println("user.resource: " + bean.resource);
    }
}
