package com.chaytech.spring.bean.factory;

import com.chaytech.spring.common.domain.User;

/**
 * user工厂类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
