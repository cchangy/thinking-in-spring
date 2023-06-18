package com.chency.spring.bean.data.conversion;

import com.chency.spring.common.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * Spring 自定义 {@link PropertyEditor} 示例
 *
 * @author cchangy
 * @date 2023/06/18 11:52
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:property-editors-context.xml");

        User user = context.getBean("user", User.class);
        System.out.println(user);

        context.close();
    }
}
