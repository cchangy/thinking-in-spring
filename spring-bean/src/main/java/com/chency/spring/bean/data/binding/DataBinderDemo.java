package com.chency.spring.bean.data.binding;

import com.chency.spring.common.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 示例
 *
 * @author cchangy
 * @date 2023/06/17 10:15
 */
public class DataBinderDemo {

    public static void main(String[] args) {

        // 创建空白对象
        User user = new User();
        DataBinder binder = new DataBinder(user);

        // 创建PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("name", "chency");

        /**
         * PropertyValues中有一个不存在的属性
         * 特性一：忽略未知属性
         */
        source.put("id", 1L);

        /**
         * PropertyValues存在一个嵌套属性
         * 特性二：支持嵌套属性
         */
//        source.put("company", new Company());
        source.put("company.name", "xxx科技有限公司");

        PropertyValues propertyValues = new MutablePropertyValues(source);

        // 1. 忽略未知属性，默认true，false时属性不存在会抛出异常
//        binder.setIgnoreUnknownFields(false);

        // 2. 自动添加嵌套路径，默认true，false时不会自动创建嵌套对象
        binder.setAutoGrowNestedPaths(false);

        // 3. 忽略嵌套属性校验，默认为false，需配合AutoGrowNestedPaths使用
        binder.setIgnoreInvalidFields(true);

        // 4. 设置必填属性
        binder.setRequiredFields("city");

        binder.bind(propertyValues);

        // 输出user内容
        System.out.println(user);

        // 获取绑定结果（结果会包含校验结果，并不会抛出异常）
        BindingResult bindingResult = binder.getBindingResult();
        System.out.println(bindingResult);
    }
}
