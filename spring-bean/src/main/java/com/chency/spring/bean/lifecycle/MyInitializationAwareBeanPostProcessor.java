package com.chency.spring.bean.lifecycle;

import com.chency.spring.common.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * bean初始化后置处理器
 *
 * @author chency
 * @date 2022/10/29 18:55
 */
public class MyInitializationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * bean初始化之前
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            System.out.println("User Bean 初始化之前...");
        }
        return bean;
    }

    /**
     * bean初始化之前
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            System.out.println("User Bean 初始化之后...");
        }
        return bean;
    }
}
