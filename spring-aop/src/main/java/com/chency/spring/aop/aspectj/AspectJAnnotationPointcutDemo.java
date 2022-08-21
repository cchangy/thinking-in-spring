package com.chency.spring.aop.aspectj;

import com.chency.spring.aop.aspectj.aspect.AspectConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Random;

/**
 * aspectJ Pointcut 示例
 *
 * @author chency
 * @date 2022/08/21 09:27
 */
@Configuration
@EnableAspectJAutoProxy
@Slf4j
public class AspectJAnnotationPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotationPointcutDemo.class, AspectConfiguration.class);
        context.refresh();

        AspectJAnnotationPointcutDemo bean = context.getBean(AspectJAnnotationPointcutDemo.class);
        bean.execute();

        context.close();
    }

    public void execute() {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("throw exception");
        }
        log.info("execute...");
    }
}

