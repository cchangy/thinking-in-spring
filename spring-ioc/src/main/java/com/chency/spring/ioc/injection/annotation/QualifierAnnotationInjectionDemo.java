package com.chency.spring.ioc.injection.annotation;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 *  {@link Qualifier} 注解依赖注入示例
 *
 * @author chency
 * @date 2022/05/05 21:35
 */
public class QualifierAnnotationInjectionDemo {

    @Autowired
    private User vipUser;

    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    private List<User> groupByAutowired;

    @Autowired
    @Qualifier
    private List<User> groupByQualifier;

    @Autowired
    @UserGroup
    private List<User> groupByUser;

    @Bean
    @Qualifier
    public User user1() {
        User user = new User();
        user.setName("user1");
        return user;
    }

    @Bean
    @UserGroup
    public User user2() {
        User user = new User();
        user.setName("user2");
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setName("user3");
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setName("user4");
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(QualifierAnnotationInjectionDemo.class);
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("dependent-lookup-context.xml");
        context.refresh();

        QualifierAnnotationInjectionDemo bean = context.getBean(QualifierAnnotationInjectionDemo.class);
        System.out.println("vipUser: " + bean.vipUser);
        System.out.println("user: " + bean.user);
        System.out.println("groupByAutowired-" + bean.groupByAutowired.size() + ": " + bean.groupByAutowired);
        System.out.println("groupByQualifier-" + bean.groupByQualifier.size() + ": " + bean.groupByQualifier);
        System.out.println("groupByUser-" + bean.groupByUser.size() + ": " + bean.groupByUser);
    }

}
