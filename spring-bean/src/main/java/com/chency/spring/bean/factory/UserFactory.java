package com.chency.spring.bean.factory;

import com.chency.spring.common.domain.User;

/**
 * user工厂类
 *
 * @author chency
 * @date 2022/05/02
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
