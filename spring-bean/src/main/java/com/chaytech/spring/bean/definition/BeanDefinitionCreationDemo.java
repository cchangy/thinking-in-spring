package com.chaytech.spring.bean.definition;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * BeanDefinition构建示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1. 通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性值
        beanDefinitionBuilder.addPropertyValue("name", "chency");
        beanDefinitionBuilder.addPropertyValue("age", 18);
        // 获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2. 通过AbstractBeanDefinition以及子类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        // 批量添加属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        // propertyValues.addPropertyValue("name", "chency");
        // propertyValues.addPropertyValue("age", 18);
        propertyValues.add("name", "chency")
                .add("age", 18);
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
