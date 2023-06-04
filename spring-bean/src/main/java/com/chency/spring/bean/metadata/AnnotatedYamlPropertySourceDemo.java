package com.chency.spring.bean.metadata;

import com.chency.spring.common.domain.User;
import com.chency.spring.common.enums.CityEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于注解的YAML 外部化配置示例
 *
 * @author cchangy
 * @date 2023/06/04 20:27
 */
@PropertySource(name = "yamlPropertySource", value = "classpath:META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.name}") String name, @Value("${user.city}") String city) {
        User user = new User();
        user.setName(name);
        user.setCity(CityEnum.valueOf(city));
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedYamlPropertySourceDemo.class);
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();
    }
}
