package com.chency.spring.common.domain;

import com.chency.spring.common.enums.CityEnum;
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
import java.util.Arrays;
import java.util.List;

/**
 * 执行顺序：AbstractAutowireCapableBeanFactory#invokeAwareMethods
 *  BeanNameAware
 *  BeanClassLoaderAware
 *  BeanFactoryAware
 *
 * applicationAware: ApplicationContextAwareProcessor#invokeAwareInterfaces
 *  EnvironmentAware
 *
 * @author chency
 * @Date 2022/05/02
 */
public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {

    private String name;
    private Integer age;
    private CityEnum city;
    private Resource configLocation;
    private CityEnum[] workCity;
    private List<CityEnum> lifeCitys;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CityEnum getCity() {
        return city;
    }

    public void setCity(CityEnum city) {
        this.city = city;
    }

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public CityEnum[] getWorkCity() {
        return workCity;
    }

    public void setWorkCity(CityEnum[] workCity) {
        this.workCity = workCity;
    }

    public List<CityEnum> getLifeCitys() {
        return lifeCitys;
    }

    public void setLifeCitys(List<CityEnum> lifeCitys) {
        this.lifeCitys = lifeCitys;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", configLocation=" + configLocation +
                ", workCity=" + Arrays.toString(workCity) +
                ", lifeCitys=" + lifeCitys +
                ", beanName='" + beanName + '\'' +
                ", classLoader=" + classLoader +
                ", beanFactory=" + beanFactory +
                '}';
    }

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
