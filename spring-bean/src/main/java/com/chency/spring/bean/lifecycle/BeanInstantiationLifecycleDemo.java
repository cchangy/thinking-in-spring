package com.chency.spring.bean.lifecycle;

import com.chency.spring.common.domain.VipUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * bean实例化生命周期示例
 *
 * @author chency
 * @date 2022/07/31 16:41
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beanNum = beanDefinitionReader.loadBeanDefinitions("dependent-lookup-context.xml");
        System.out.println("加载的BeanDefinition数量：" + beanNum);

        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory.getBean("vipUser"));
    }


    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("vipUser", beanName) && VipUser.class.equals(beanClass)) {
                return new VipUser();
            }

            return null;
        }
    }
}
