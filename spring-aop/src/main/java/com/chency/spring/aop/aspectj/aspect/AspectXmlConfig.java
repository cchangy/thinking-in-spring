package com.chency.spring.aop.aspectj.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 基于xml的aspect配置类
 *
 * @author chency
 * @date 2022/08/21 09:44
 */
@Slf4j
public class AspectXmlConfig {

    public Object aroundAnyPublicMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("@Around any public method: {}", joinPoint.getSignature().getName());
        return joinPoint.proceed(); // 需要显示触发调用目标方法
    }

    public void beforeAnyPublicMethod() {
        log.info("@Before any public method");
    }

    public void afterAnyPublicMethod() {
        log.info("@After any public method");
    }

    public void afterReturningAnyPublicMethod() {
        log.info("@AfterRetuning any public method");
    }

    public void afterThrowingAnyPublicMethod() {
        log.info("@AfterThrowing any public method");
    }
}
