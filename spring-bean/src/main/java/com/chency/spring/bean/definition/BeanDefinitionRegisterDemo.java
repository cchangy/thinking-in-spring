package com.chency.spring.bean.definition;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.support.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;

/**
 * BeanDefinition注册示例
 *
 * @author chency
 * @date 2022/05/02
 */
public class BeanDefinitionRegisterDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // 命名方式注册
        registerUserBeanDefinition(context, "user-chen");
        // 非命名方式注册
        registerUserBeanDefinition(context);

        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "chen");
        beanDefinitionBuilder.addPropertyValue("age", 18);

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            // 命名方式
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            // 非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }
}
