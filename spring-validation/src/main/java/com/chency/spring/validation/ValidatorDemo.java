package com.chency.spring.validation;

import com.chency.spring.common.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;

import java.util.Locale;

/**
 * 自定义 spring {@link Validator} 示例
 *
 * @author cchangy
 * @date 2023/06/16 07:16
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        Validator validator = new UserValidator();
        User user = new User();
        System.out.println("User 对象是否支持UserValidator校验=" + validator.supports(user.getClass()));

        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        MessageSource messageSource = createMessageSource();
        for (ObjectError error : errors.getAllErrors()) {
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

    static class UserValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        }
    }
}
