package com.chency.spring.bean.metadata;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * user.xsd {@link NamespaceHandler} 实现
 *
 * @author cchangy
 * @date 2023/06/04 19:12
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 注册user元素的BeanDefinitionParser实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
