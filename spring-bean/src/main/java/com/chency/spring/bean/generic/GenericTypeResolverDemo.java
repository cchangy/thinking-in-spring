package com.chency.spring.bean.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link GenericTypeResolver} 示例
 *
 * @author cchangy
 * @date 2023/06/18 20:39
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws Exception {
        // String Comparable<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");

        // ArrayList<String> 是List泛型参数类型具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

        // StringList 也是List泛型参数类型具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        // typeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentTypes) throws Exception {
        Method method = containingClass.getMethod(methodName, argumentTypes);

        // 获取返回类型
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

        // 常规类型不具备泛型参数类型
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", methodName, containingClass.getSimpleName(), returnTypeArgument);
    }

    public static StringList getStringList() {
        return null;
    }

    public static ArrayList<String> getList() { // 泛型参数类型具体化
        return null;
    }

    public static String getString() {
        return null;
    }

}
