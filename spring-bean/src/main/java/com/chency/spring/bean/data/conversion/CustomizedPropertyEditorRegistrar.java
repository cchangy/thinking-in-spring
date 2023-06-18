package com.chency.spring.bean.data.conversion;

import com.chency.spring.common.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义 {@link PropertyEditorRegistrar} 示例
 *
 * @author cchangy
 * @date 2023/06/18 11:49
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 注册PropertyEditor
        registry.registerCustomEditor(User.class, "properties", new StringToPropertiesPropertyEditor());
    }
}
