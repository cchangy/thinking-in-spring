package com.chency.spring.bean.data.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} 示例
 *
 * @author cchangy
 * @date 2023/06/18 11:26
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {

        String text = "name=chency";
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        // 传递内容
        propertyEditor.setAsText(text);
        // 获取value
        System.out.println("value= " + propertyEditor.getValue());
        System.out.println("text= " + propertyEditor.getAsText());
    }
}
