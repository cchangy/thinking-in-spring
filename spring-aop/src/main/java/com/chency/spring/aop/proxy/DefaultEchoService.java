package com.chency.spring.aop.proxy;

import java.util.Random;

/**
 * {@link EchoService} 默认实现
 *
 * @author chency
 * @date 2022/08/20 16:21
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("throw exception");
        }
        return "[echo] " + message;
    }
}
