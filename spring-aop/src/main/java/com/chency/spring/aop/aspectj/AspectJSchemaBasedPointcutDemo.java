package com.chency.spring.aop.aspectj;

import com.chency.spring.aop.proxy.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于xml配置的pointcut示例
 *
 * @author chency
 * @date 2022/08/21 09:40
 */
@Slf4j
public class AspectJSchemaBasedPointcutDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop-context.xml");
        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);
        echoService.echo("xml pointcut");

        context.close();
    }
}
