package com.chency.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} 示例
 *
 * @author cchangy
 * @date 2023/06/14 07:10
 */
public class MessageFormatDemo {

    public static void main(String[] args) {

        int planet = 7;
        String event = "a disturbance in the Force";

        MessageFormat messageFormat = new MessageFormat("At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.");
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置MessageFormatPattern
        messageFormat.applyPattern("This is a text: {0}, {1}, {2}");
        result = messageFormat.format(new Object[]{"Hello World", "666"});
        System.out.println(result);

        // 重置Locale
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern("At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.");
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置Format
        // 根据参数索引来设置Pattern
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
