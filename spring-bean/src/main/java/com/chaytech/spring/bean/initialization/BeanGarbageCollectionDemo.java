package com.chaytech.spring.bean.initialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * bean垃圾回收示例
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/05/02 23:04
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(BeanInitializationDemo.class);
        context.refresh();
        context.close();
        TimeUnit.SECONDS.sleep(5);
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }
}
