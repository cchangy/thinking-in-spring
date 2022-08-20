package com.chency.spring.aop.proxy;

/**
 * echo服务
 *
 * @author chency
 * @date 2022/08/20 16:20
 */
public interface EchoService {

    String echo(String message) throws NullPointerException;

}
