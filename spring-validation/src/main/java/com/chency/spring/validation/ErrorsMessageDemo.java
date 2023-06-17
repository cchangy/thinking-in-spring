package com.chency.spring.validation;

import com.chency.spring.common.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * 错误文案示例
 *
 * @author cchangy
 * @date 2023/06/16 06:49
 */
public class ErrorsMessageDemo {

    public static void main(String[] args) {
        // 创建user对象
        User user = new User();
        // 选择BeanPropertyBindingResult（Errors）
        Errors errors = new BeanPropertyBindingResult(user, "user");

        // 调用 reject 或 rejectValue
        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");

        // 获取 Errors 中的 ObjectErrors 和 FiledError
        // FiledError is ObjectError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();
        
        // 通过 ObjectErrors 和 FieldError 中的 code 和 args 来关联 MessageSource实现
        MessageSource messageSource = createMessageSource();

        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null");
        return messageSource;
    }
}
