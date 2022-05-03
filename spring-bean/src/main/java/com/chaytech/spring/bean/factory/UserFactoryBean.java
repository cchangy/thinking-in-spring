package com.chaytech.spring.bean.factory;

import com.chaytech.spring.common.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * user factoryBean的实现
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
