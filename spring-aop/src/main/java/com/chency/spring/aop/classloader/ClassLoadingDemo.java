package com.chency.spring.aop.classloader;

import lombok.extern.slf4j.Slf4j;

/**
 * 类加载实例
 *
 * @author chency
 * @date 2022/08/20 14:42
 */
@Slf4j
public class ClassLoadingDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        log.info("classLoader={}", classLoader);
        log.info("parentClassLoader={}", classLoader.getParent());
    }
}
