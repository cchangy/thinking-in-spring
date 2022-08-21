package com.chency.spring.aop.aspectj;

import com.chency.spring.aop.proxy.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * aop proxyFactoryBean 示例
 *
 * @author chency
 * @date 2022/08/21 09:04
 */
@Slf4j
public class AopProxyFactoryBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop-context.xml");

        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);
        log.info(echoService.echo("aop proxy factory bean"));

        context.close();
    }
}
