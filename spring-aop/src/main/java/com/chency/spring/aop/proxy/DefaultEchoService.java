package com.chency.spring.aop.proxy;

/**
 * echo默认实现
 *
 * @author chency
 * @date 2022/08/20 16:21
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[echo]" + message;
    }
}
