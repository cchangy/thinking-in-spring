package com.chency.spring.aop.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * aspectj 注解形式示例
 *
 * @author chency
 * @date 2022/08/21 08:32
 */
@Aspect // 声明为aspect切面
@Configuration
@EnableAspectJAutoProxy
@Slf4j
public class AspectJAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotationDemo.class);
        context.refresh();

        AspectJAnnotationDemo aspectJDemo = context.getBean(AspectJAnnotationDemo.class);
        log.info("aspectJDemo={}", aspectJDemo);
        context.close();
    }
}
