package com.chaytech.spring.bean.definition;

import com.chaytech.spring.common.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean别名示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean-definition-context.xml");

        // 通过别名获取
        User chen = context.getBean("chen", User.class);
        // 通过名称获取
        User user = context.getBean("user", User.class);
        System.out.println("别名user和userBean是否相等: " + (chen == user));
    }
}
