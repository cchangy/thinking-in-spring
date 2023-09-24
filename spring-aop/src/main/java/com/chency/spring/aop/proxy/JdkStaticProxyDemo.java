package com.chency.spring.aop.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * jdk静态代理示例
 *
 * @author chency
 * @date 2022/08/20 16:20
 */
@Slf4j
public class JdkStaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        log.info(echoService.echo("jdk static proxy"));
    }
}
