package com.chency.spring.resource;

import com.chency.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 *
 * @author cchangy
 * @date 2023/06/08 07:00
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        // 读取当前package对应所有的java文件
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/spring-resource/src/main/java/com/chency/spring/resource/";
        String locationPattern = currentJavaFilePath + "*.java";

        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        resourcePatternResolver.setPathMatcher(new JavaFilePathMatcher());

        Resource[] resources = resourcePatternResolver.getResources(locationPattern);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    /**
     * 自定义 {@link PathMatcher} 示例
     */
    static class JavaFilePathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return isPattern(path);
        }

        @Override
        public boolean matchStart(String s, String s1) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String s, String s1) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String s, String s1) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String s) {
            return null;
        }

        @Override
        public String combine(String s, String s1) {
            return null;
        }
    }
}
