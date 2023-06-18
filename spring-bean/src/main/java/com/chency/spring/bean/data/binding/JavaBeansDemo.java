package com.chency.spring.bean.data.binding;

import com.chency.spring.common.domain.User;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * Java beans 示例
 *
 * @author cchangy
 * @date 2023/06/18 09:44
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws Exception{

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);

        // 属性描述 PropertyDescriptors
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);

        System.out.println("----------------------");

        // 方法描述 MethodDescriptors
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }

}
