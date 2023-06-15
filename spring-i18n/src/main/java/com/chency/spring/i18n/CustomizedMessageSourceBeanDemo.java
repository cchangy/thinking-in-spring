package com.chency.spring.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Spring Boot场景下自定义 {@link MessageSource} bean
 *
 * @author cchangy
 * @date 2023/06/15 06:58
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {

    /**
     * 在Spring Boot场景中，Primary Configuration Source(Classes) 高于 *AutoConfiguration
     *
     * @return
     */
    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class).web(WebApplicationType.NONE).run(args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));

            MessageSource messageSource = applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }

        applicationContext.close();
    }
}
