package com.chency.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义 @Enable 注解
 *
 * @author cchangy
 * @date 2023/06/26 07:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) // 方法一：通过Configuration class 实现
//@Import(HelloWorldImportSelector.class) // 方法二：通过ImportSelector 接口实现
@Import(HelloWorldImportBeanDefinitionRegistry.class) // 方法三：通过ImportBeanDefinitionRegistry 接口实现
public @interface EnableHelloWorld {

}
