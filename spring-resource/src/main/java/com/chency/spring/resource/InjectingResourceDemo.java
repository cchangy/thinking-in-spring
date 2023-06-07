package com.chency.spring.resource;

import com.chency.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 示例
 *
 * @author cchangy
 * @date 2023/06/08 07:16
 */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("${user.dir}")
    private String userDir;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
        System.out.println("----------------");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("----------------");
        System.out.println(userDir);

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceDemo.class);

        context.refresh();
        context.close();
    }
}
