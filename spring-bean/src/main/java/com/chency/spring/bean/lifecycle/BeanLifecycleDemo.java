package com.chency.spring.bean.lifecycle;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * bean完成生命周期实例
 *
 * @author chency
 * @date 2022/10/29 20:04
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加bean初始化后置处理器：MyInitializationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInitializationAwareBeanPostProcessor());
        // 添加bean销毁后置处理：MyDestructionAwarePostProcessor
        beanFactory.addBeanPostProcessor(new MyDestructionAwarePostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanNum = beanDefinitionReader.loadBeanDefinitions("dependent-lookup-context.xml");
        System.out.println("加载的BeanDefinition数量：" + beanNum);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        // 销毁bean，并不意味着bean被垃圾回收掉了
        beanFactory.destroyBean("user", user);
    }
}
