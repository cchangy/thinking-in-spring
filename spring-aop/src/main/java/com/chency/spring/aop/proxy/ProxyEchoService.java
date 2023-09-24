package com.chency.spring.aop.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link EchoService} 静态代理类
 *
 * @author chency
 * @date 2022/08/20 16:22
 */
@Slf4j
public class ProxyEchoService implements EchoService{

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        log.info("echo start...");
        String result = echoService.echo(message);
        log.info("echo end...");
        return result;
    }
}
