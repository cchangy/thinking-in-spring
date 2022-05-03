package com.chaytech.spring.ioc.injection;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于API的setter方法注入示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/03 22:19
 */
public class APISetterInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(APISetterInjectionDemo.class);
        context.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());
        context.refresh();

        UserHolder userHolder = context.getBean(UserHolder.class);
        System.out.println(userHolder);
    }

    /**
     * 为UserHolder生成BeanDefinition
     *
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        builder.addPropertyReference("user", "user");
        return builder.getBeanDefinition();
    }

    @Bean
    public User user() {
        return User.createUser();
    }
}
