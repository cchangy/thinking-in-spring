package com.chency.spring.ioc.repository;

import com.chency.spring.common.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 用户 repository
 *
 * @author chency
 * @Date 2022/05/02
 */
public class UserRepository {

    private List<User> users; // 自定义bean

    private BeanFactory beanFactory; // 内建的非bean对象

    private ObjectFactory<ApplicationContext> objectFactory;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }
}
