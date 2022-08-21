package com.chency.spring.aop.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * aspectj xml形式示例
 *
 * @author chency
 * @date 2022/08/21 08:32
 */
@Aspect // 声明为aspect切面
@Configuration
@Slf4j
public class AspectJXmlDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop-context.xml");

        context.close();
    }
}
