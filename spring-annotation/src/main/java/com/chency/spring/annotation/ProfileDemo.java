package com.chency.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link Profile} 示例
 *
 * @author cchangy
 * @date 2023/06/27 21:27
 */
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();
        // 设置默认 profiles
//        environment.setDefaultProfiles("odd");
//        environment.setActiveProfiles("even");

        context.refresh();

        Integer number = context.getBean("number", Integer.class);
        System.out.println(number);

        context.close();
    }

    @Bean(name = "number")
    @Profile("odd") // 奇数
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even") // 偶数
    @Conditional(EvenProfileCondition.class)
    public Integer even() {
        return 2;
    }
}
