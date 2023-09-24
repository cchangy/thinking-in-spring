package com.chency.spring.aop.pointcut;

import com.chency.spring.aop.proxy.EchoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author chency
 * @date 2022/08/21 09:55
 */
public class EchoServiceEchoMethodPointcut implements Pointcut {

    private static final EchoServiceEchoMethodPointcut POINTCUT = new EchoServiceEchoMethodPointcut();

    private EchoServiceEchoMethodPointcut() {
    }

    public static EchoServiceEchoMethodPointcut getInstance() {
        return POINTCUT;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return EchoService.class.isAssignableFrom(clazz);
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "echo".equals(method.getName())
                        && method.getParameterTypes().length == 1;
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
