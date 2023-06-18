package com.chency.spring.bean.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} 示例
 *
 * @author cchangy
 * @date 2023/06/18 21:18
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {

        // StringList -> ArrayList -> AbstractList -> AbstractCollection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        System.out.println(resolvableType.getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType().getSuperType());

        System.out.println(resolvableType.asCollection().resolve()); // 获取raw type
        System.out.println(resolvableType.asCollection().resolveGeneric(0));
    }
}
