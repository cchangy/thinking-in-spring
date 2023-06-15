package com.chency.spring.i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * {@link DynamicResourceMessageSource} 测试
 *
 * @author cchangy
 * @date 2023/06/15 07:59
 */
public class DynamicResourceMessageSourceTest {

    @Test
    public void testDynamicUpdateMessageSource() throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        while (true) {
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
