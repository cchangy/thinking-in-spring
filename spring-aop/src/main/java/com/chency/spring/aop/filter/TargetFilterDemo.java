package com.chency.spring.aop.filter;

import com.chency.spring.aop.proxy.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 目标过滤示例
 *
 * @author chency
 * @date 2022/08/20 16:33
 */
@Slf4j
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 获取目标类全限定名
        String targetClassName = EchoService.class.getName();
        // 获取当前线程classLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类
        Class<?> targetClass = classLoader.loadClass(targetClassName);
        /**
         * 查找某个方法，如果未找到会返回null
         * param1：目标类
         * param2：目标方法名
         * param...: 目标方法参数类型
         */
        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        log.info("targetMethod={}", targetMethod);

        // 查找throws类型为NullPointerException的方法
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                log.info("仅抛出NullPointerException的方法为: {}", method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                // 方法参数类型
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 方法异常类型
                Class<?>[] exceptionTypes = method.getExceptionTypes();
                return exceptionTypes.length == 1 && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });
    }
}
