package com.chency.spring.resource;

import com.chency.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

/**
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * @author cchangy
 * @date 2023/06/08 06:39
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/spring-resource/src/main/java/com/chency/spring/resource/EncodedFileSystemResourceDemo.java";
        System.out.println("filePath=" + currentJavaFilePath);

        // 新建一个FileSystemResourceLoader对象
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();

        // 通过resourceLoader获取resource
        Resource resource = fileSystemResourceLoader.getResource(currentJavaFilePath);
        System.out.println(ResourceUtils.getContent(resource));
    }
}
