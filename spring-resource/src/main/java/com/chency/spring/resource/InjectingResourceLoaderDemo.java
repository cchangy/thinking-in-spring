package com.chency.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link ResourceLoader} 示例
 *
 * @author cchangy
 * @date 2023/06/08 07:28
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    // 方法一：实现ResourceLoaderAware回调
    private ResourceLoader resourceLoader;

    // 方法二：注入ResourceLoader
    @Autowired
    private ResourceLoader autowiredResourceLoader;

    // 方法三：注入ApplicationContext作为ResourceLoader
    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("resourceLoader == autowiredResourceLoader: " + (resourceLoader == autowiredResourceLoader));
        System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceLoaderDemo.class);

        context.refresh();
        context.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
