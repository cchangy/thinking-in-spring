package com.chency.spring.bean.scope;

import com.chency.spring.common.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;
import java.util.UUID;

/**
 * bean的作用域示例
 *
 * singleton: {@link ConfigurableBeanFactory.SCOPE_SINGLETON}
 * prototype: {@link ConfigurableBeanFactory.SCOPE_PROTOTYPE}
 *
 * @author chency
 * @date 2022/05/11 06:18
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    // 可以不用指定，默认就是singleton
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public User singletonUser() {
        User user = User.createUser();
        user.setName(UUID.randomUUID().toString());
        return user;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        User user = User.createUser();
        user.setName(UUID.randomUUID().toString());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    private List<User> userList;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeDemo.class);
        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean名称: %s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });
        context.refresh();

        /**
         * 结论一：
         *      singleton bean无论依赖查找还是依赖注入，均为同一个对象
         *      prototype bean无论依赖注入还是依赖查找，均为新生成对象
         *
         * 结论二：
         *      如果依赖注入集合类型，singleton bean和prototype bean均只会存在一个
         *
         * 结论三：
         *      无论是singleton bean还是prototype bean均会执行初始化方法回调
         *      不过仅singleton bean会执行销毁方法回调
         */
        scopeBeansByLookup(context);
        scopeBeansByInjection(context);

        context.close();
    }

    private static void scopeBeansByLookup(AbstractApplicationContext context) {
        for (int i = 0; i < 2; i++) {
            System.out.println("singletonUser: " + context.getBean("singletonUser", User.class));
            System.out.println("prototypeUser:" + context.getBean("prototypeUser", User.class));
        }
    }

    private static void scopeBeansByInjection(AbstractApplicationContext context) {
        BeanScopeDemo bean = context.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser: "+ bean.singletonUser);
        System.out.println("singletonUser1: "+ bean.singletonUser1);
        System.out.println("prototypeUser: "+ bean.prototypeUser);
        System.out.println("prototypeUser1: "+ bean.prototypeUser1);
        System.out.println("userList: "+ bean.userList);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanScopeDemo prototype bean 销毁中....");

        // 显示调用销毁方法来执行原型模式bean的销毁
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();

        for (User user : userList) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(user.getBeanName());
            if (beanDefinition.isPrototype()) {
                user.destroy();
            }
        }

        System.out.println("BeanScopeDemo prototype bean 销毁结束....");
    }
}
