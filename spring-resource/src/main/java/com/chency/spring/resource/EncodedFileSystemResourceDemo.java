package com.chency.spring.resource;

import com.chency.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResource;

/**
 * 带有字符编码的 {@link FileSystemResource} 示例
 *
 * @author cchangy
 * @date 2023/06/08 06:39
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) {
        String currentJavaFilePath = System.getProperty("user.dir") + "/spring-resource/src/main/java/com/chency/spring/resource/EncodedFileSystemResourceDemo.java";
        System.out.println("filePath=" + currentJavaFilePath);

        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);
        System.out.println(ResourceUtils.getContent(fileSystemResource));
    }
}
