package com.chaytech.spring.common.domain;

/**
 * @author chency
 * @email chaytech@163.com
 * @Date 2022/05/02
 */
public class User {

    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setName("chency");
        user.setAge(18);
        return user;
    }


}
