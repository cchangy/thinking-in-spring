package com.chency.spring.aop.pointcut;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 代码实现 pointcut 示例
 *
 * @author chency
 * @date 2022/08/21 09:55
 */
@AllArgsConstructor
@NoArgsConstructor
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

    private String methodName;
    private Class targetClass;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (Objects.equals(this.methodName, method.getName()) && this.targetClass.isAssignableFrom(targetClass)) {
            return true;
        }
        return false;
    }
}
