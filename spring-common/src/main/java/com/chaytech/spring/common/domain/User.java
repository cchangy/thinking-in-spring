package com.chaytech.spring.common.domain;

import com.chaytech.spring.common.enums.CityEnum;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * @author chency
 * @email chaytech@163.com
 * @Date 2022/05/02
 */
public class User implements BeanNameAware {

    private String name;
    private Integer age;
    private CityEnum city;
    private Resource configLocation;
    private CityEnum[] workCity;
    private List<CityEnum> lifeCitys;

    private transient String beanName;

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

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
