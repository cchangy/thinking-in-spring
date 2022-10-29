package com.chency.spring.bean.initialization;

import com.chency.spring.bean.factory.DefaultUserFactory;
import com.chency.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * bean初始化示例
 * <p>
 * 初始化顺序：@PostConstruct -> InitializingBean.afterPropertiesSet() -> initMethod
 * 销毁顺序：@PreDestroy -> DisposableBean.destroy() -> destroyMethod
 * @author chency
 * @date 2022/05/02 20:51
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(BeanInitializationDemo.class);
        context.refresh();

        // 非延迟加载会在Spring应用上下文启动完成之前被初始化
        System.out.println("Spring 应用上下文已启动...");

        UserFactory userFactory = context.getBean(UserFactory.class);
        System.out.println(userFactory);

        // 关闭应用上下文
        System.out.println("Spring 应用上下文准备关闭...");
        context.close();
        System.out.println("Spring 应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy(value = false) // 延迟加载
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
