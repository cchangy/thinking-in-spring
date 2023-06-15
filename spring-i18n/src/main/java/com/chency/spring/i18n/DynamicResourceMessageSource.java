package com.chency.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 动态（更新）资源 {@link MessageSource} 实现
 * <p>
 * 实现步骤：
 * <p>
 * 1. 定位资源（文件）位置
 * 2. 初始化properties对象
 * 3. 实现AbstractMessageSource#resolveCode方法
 * 4. 监听资源文件（Java NIO WatchService）
 * 5. 使用线程池处理文件变化
 * 6. 重新装载properties对象
 *
 * @author cchangy
 * @date 2023/06/15 07:19
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String RESOURCE_FILE_NAME = "msg.properties";

    private static final String RESOURCE_PATH = "/META-INF/" + RESOURCE_FILE_NAME;

    private static final String ENCODING = "UTF-8";

    private final Resource messagePropertiesResource;

    private final Properties messageProperties;

    private final ExecutorService executorService;

    private ResourceLoader resourceLoader;

    public DynamicResourceMessageSource() {
        this.messagePropertiesResource = getMessagePropertiesResource();
        this.messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();

        // 监听资源文件变化
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        // 判断是否是为文件
        if (this.messagePropertiesResource.isFile()) {
            try {
                File messagePropertiesFile = this.messagePropertiesResource.getFile();
                Path messagePropertiesFilePath = messagePropertiesFile.toPath();

                // 获取当前OS文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                // 新建WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 获取资源文件所在的目录
                Path parentPath = messagePropertiesFilePath.getParent();
                // 注册WatchService到parentPath，并绑定修改事件
                parentPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                // 处理资源文件变化（异步）
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理资源文件变化（异步）
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take(); // take会发生阻塞
                // watchKey是否有效
                try {
                    if (watchKey.isValid()) {
                        for (WatchEvent event : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            // 监听的注册目录
                            Path path = (Path) watchable;
                            // 时间锁关联的对象即注册目录的子文件或子目录
                            // 事件发生源是相对路径
                            Path fileRelativePath = (Path) event.context();
                            if (RESOURCE_FILE_NAME.equals(fileRelativePath.getFileName().toString())) {
                                // 处理为绝对路径
                                Path filePath = path.resolve(fileRelativePath);
                                File file = filePath.toFile();
                                Properties properties = loadMessageProperties(new FileReader(file));
                                synchronized (messageProperties) {
                                    messageProperties.clear();
                                    messageProperties.putAll(properties);
                                }
                                System.out.println("修改的文件路径=" + filePath);
                            }
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        watchKey.reset(); // 重置watchKey
                    }
                }
            }
        });
    }

    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, ENCODING);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }

    private Resource getMessagePropertiesResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        return resourceLoader.getResource(RESOURCE_PATH);
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
