package com.chency.spring.bean.metadata;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 外部化配置配置示例
 *
 * @author cchangy
 * @date 2023/06/04 18:42
 */
@PropertySource("classpath:META-INF/user.properties")
public class PropertySourceDemo {

    @Bean
    public User propertySourceUser(@Value("${user.name}") String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertySourceDemo.class);

        // 扩展environment中PropertySource
        // 添加PropertySource操作必须在refresh方法之前
        Map<String, Object> propertySource = new HashMap<>();
        propertySource.put("user.name", "Java 666");
        context.getEnvironment().getPropertySources().addFirst(new MapPropertySource("customerPropertySource", propertySource));

        context.refresh();

        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(String.format("User Bean name=%s, content=%s", entry.getKey(), entry.getValue()));
        }

        Iterator<org.springframework.core.env.PropertySource<?>> iterator = context.getEnvironment().getPropertySources().iterator();
        while (iterator.hasNext()) {
            System.out.println("PropertySource: " + iterator.next());
        }
        context.close();
    }
}
