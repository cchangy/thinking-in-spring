package com.chency.spring.bean.lifecycle;

import com.chency.spring.common.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * InstantiationAwareBeanPostProcessor：bean实例化之前的后置处理器
 *
 * @author chency
 * @date 2022/10/29 17:22
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * bean实例化之前
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(beanClass)) {
            System.out.println("User Bean 实例化之前...");
        }

        return null;
    }

    /**
     * bean实例化之后
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
           /* // 手动赋值
            User user = (User) bean;
            user.setName("小王");

            // 返回false表示不允许属性赋值
            return false;*/
            System.out.println("User Bean 实例化之后");
        }
        return true;
    }

    /**
     * 属性赋值前回调
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            System.out.println("User Bean 属性赋值前回调...");
            MutablePropertyValues propertyValues = null;
            if (pvs instanceof  MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            if (!propertyValues.contains("configLocation")) {
                propertyValues.addPropertyValue("configLocation", "/user/chency");
            }
            return propertyValues;
        }
        return null;
    }
}