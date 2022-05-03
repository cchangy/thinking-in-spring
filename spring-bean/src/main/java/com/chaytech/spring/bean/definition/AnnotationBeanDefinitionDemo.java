package com.chaytech.spring.bean.definition;

import com.chaytech.spring.common.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 注解的BeanDefinition示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
@Import(AnnotationBeanDefinitionDemo.Config.class) // 3. 通过@Import导入
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 注解的ioc容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册配置类
        context.register(AnnotationBeanDefinitionDemo.class);
        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    @Component // 2. 通过@Component定义当前类作为spring bean组件
    public static class Config {

        @Bean(name = {"user", "chen"}) // 1. 通过@Bean定义bean
        public User user() {
            User user = new User();
            user.setName("chency");
            user.setAge(18);
            return user;
        }
    }
}
