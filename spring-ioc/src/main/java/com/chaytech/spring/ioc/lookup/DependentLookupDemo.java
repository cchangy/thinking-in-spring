package com.chaytech.spring.ioc.lookup;

import com.chaytech.spring.common.annotation.Vip;
import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 *
 * @author chency
 * @email chaytech@163.com
 * @Date 2022/05/02
 */
public class DependentLookupDemo {

    public static void main(String[] args) {
        // 启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependent-lookup-context.xml");

        // 通过名称查找
        lookupByName(beanFactory);
        // 通过类型查找
        lookupByType(beanFactory);
        // 通过类型查找多个对象list
        lookupListByType(beanFactory);
        // 通过注解查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> userMap = (Map) ((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Vip.class);
            System.out.println("查找到标注有 @Vip 注解的所有user对象：" + userMap);
        }
    }

    private static void lookupListByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> userMap = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("查找到的所有user对象：" + userMap);
        }

    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("根绝对象类型实时查找：" + user);
    }

    private static void lookupByName(BeanFactory beanFactory) {
        // 实时查找
        realTimeLookup(beanFactory);
        // 延时查找
        lazyLookup(beanFactory);
    }


    /**
     * 实时查找
     *
     * @param beanFactory
     */
    private static void realTimeLookup(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }

    /**
     * 延时查找
     *
     * @param beanFactory
     */
    private static void lazyLookup(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延时查找：" + user);
    }
}
