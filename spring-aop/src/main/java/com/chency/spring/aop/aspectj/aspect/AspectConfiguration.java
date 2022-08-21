package com.chency.spring.aop.aspectj.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 基于注解的aspect配置类
 *
 * @author chency
 * @date 2022/08/21 08:45
 */
@Aspect
@Slf4j
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))") // 匹配join point
    private void anyPublicMethod() {
        log.info("@Pointcut at any public method...");
    }

    @Around("anyPublicMethod()") // 执行拦截动作
    public Object aroundAnyPublicMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("@Around any public method");
        return joinPoint.proceed(); // 需要显示触发调用目标方法
    }

    @Before("anyPublicMethod()") // 执行拦截动作
    public void beforeAnyPublicMethod() {
        log.info("@Before any public method");
    }

    @After("anyPublicMethod()")
    public void afterAnyPublicMethod() {
        log.info("@After any public method");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterReturningAnyPublicMethod() {
        log.info("@AfterRetuning any public method");
    }

    @AfterThrowing("anyPublicMethod()")
    public void afterThrowingAnyPublicMethod() {
        log.info("@AfterThrowing any public method");
    }
}
