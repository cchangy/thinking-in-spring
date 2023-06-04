package com.chency.spring.bean.metadata;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于Java注解的spring ioc 容器元信息配置示例
 *
 * @author cchangy
 * @date 2023/06/04 18:42
 */
@ImportResource("classpath:dependent-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:META-INF/user.properties")
public class AnnotatedSpringIocContainerMetadataDemo {

    @Bean
    public User propertySourceUser(@Value("${user.name}") String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedSpringIocContainerMetadataDemo.class);

        context.refresh();

        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(String.format("User Bean name=%s, content=%s", entry.getKey(), entry.getValue()));
        }

        context.close();
    }
}
