package com.chency.spring.bean.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @author cchangy
 * @date 2023/06/18 20:22
 */
public class GenericDemo {

    public static void main(String[] args) {

        Collection<String> list = new ArrayList<>();
        list.add("test");
        // 编译时错误
        // list.add(1);

        // 泛型擦写
        Collection temp = list;
        temp.add(1);

        System.out.println(list);
    }
}
