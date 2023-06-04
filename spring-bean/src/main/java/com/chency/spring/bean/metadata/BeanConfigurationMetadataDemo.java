package com.chency.spring.bean.metadata;

import com.chency.spring.common.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * bean配置元信息示例
 *
 * @author chency
 * @date 2022/10/30 10:50
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        // 定义BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "chency");
        // 获取BeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 增加附加属性（辅助作用），不会改变bean原有的属性值
        beanDefinition.setAttribute("name", "xiao chen");
        // 设置来源（辅助作用）
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);

                    // 通过source来判断来源
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
                        String name = (String) bd.getAttribute("name");
                        System.out.println("从属性上下文中获取到的值：" + name);

                        // 覆盖user原有属性值
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });
        beanFactory.registerBeanDefinition("user", beanDefinition);

        System.out.println(beanFactory.getBean("user"));
    }
}
