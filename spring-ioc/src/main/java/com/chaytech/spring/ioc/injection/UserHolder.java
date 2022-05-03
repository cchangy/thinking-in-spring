package com.chaytech.spring.ioc.injection;

import com.chaytech.spring.common.domain.User;

/**
 * user holder
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/03 22:23
 */
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
