package com.chency.spring.bean.lifecycle;

import com.chency.spring.common.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * Bean销毁前后置处理器
 *
 * @author chency
 * @date 2022/10/29 20:02
 */
public class MyDestructionAwarePostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            System.out.println("User bean 销毁前...");
        }
    }
}
