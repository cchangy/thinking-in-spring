package com.chency.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author cchangy
 * @date 2023/06/26 07:13
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 导入 Configuration class
        return new String[]{"com.chency.spring.annotation.HelloWorldConfiguration"};
    }
}
