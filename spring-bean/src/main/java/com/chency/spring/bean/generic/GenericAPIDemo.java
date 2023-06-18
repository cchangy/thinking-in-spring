package com.chency.spring.bean.generic;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型 API 示例
 *
 * @author cchangy
 * @date 2023/06/18 20:28
 */
public class GenericAPIDemo {

    public static void main(String[] args) {

        // 原生类型 byte short int long ...
        Class intClass = int.class;

        // 数组类型 array int[], Object[] ...
        Class arrayClass = Object[].class;

        // 原始类型
        Class rawClass = String.class;

        // 泛型参数类型
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType);

        // 原始类型 java.util.AbstractList
        System.out.println(parameterizedType.getRawType());

        // 泛型类型 <E>
        Stream.of(parameterizedType.getActualTypeArguments()).forEach(System.out::println);
    }
}
