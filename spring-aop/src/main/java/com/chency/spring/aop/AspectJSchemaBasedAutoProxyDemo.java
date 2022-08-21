package com.chency.spring.aop;

import com.chency.spring.aop.proxy.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于xml配置的自动代理示例
 *
 * @author chency
 * @date 2022/08/21 09:40
 */
@Slf4j
public class AspectJSchemaBasedAutoProxyDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop-auto-proxy-context.xml");
        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);
        echoService.echo("xml auto proxy");

        context.close();
    }
}
