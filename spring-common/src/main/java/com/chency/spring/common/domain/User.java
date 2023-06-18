package com.chency.spring.common.domain;

import com.chency.spring.common.enums.CityEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Properties;

/**
 * 执行顺序：AbstractAutowireCapableBeanFactory#invokeAwareMethods
 * BeanNameAware
 * BeanClassLoaderAware
 * BeanFactoryAware
 * <p>
 * applicationAware: ApplicationContextAwareProcessor#invokeAwareInterfaces
 * EnvironmentAware
 *
 * @author chency
 * @Date 2022/05/02
 */
@Data
@ToString
public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {

    private String name;
    private Integer age;
    private CityEnum city;
    private Resource configLocation;
    private CityEnum[] workCity;
    private List<CityEnum> lifeCitys;

    private Company company;

    private Properties properties;

    private String propertiesText;

    public static User createUser() {
        User user = new User();
        user.setName("chency");
        user.setAge(18);
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println("User bean [" + beanName + "] 初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User bean [" + beanName + "] 销毁...");
    }

    private String beanName;
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private Environment environment;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
