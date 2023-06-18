package com.chency.spring.bean.data.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * String -> Properties {@link PropertyEditor}
 *
 * @author cchangy
 * @date 2023/06/18 11:27
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    // 1. 实现setAsText方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 2. 将String类型转换成Properties
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        // 3. 临时存储Properties对象
        super.setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) super.getValue();
        StringBuilder textBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            textBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return textBuilder.toString();
    }
}
