package io.github.zzycreate.example.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ZONE_OFFSET;

/**
 * @author zzycreate
 * @date 2019/5/10
 */
public class DateTimeStringExample {

    /**
     * 日期时间字符串
     *
     * @param str     时间字符串
     * @param pattern 日期时间格式化正则
     * @return Date
     */
    public static Date toDate(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 jdk8 的方式进行日期时间字符串的格式化
     *
     * @param str               字符串
     * @param dateTimeFormatter 格式
     * @return Date
     */
    public static Date toDateWithLocalDateTime(String str, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(str, dateTimeFormatter);
        return Date.from(localDateTime.toInstant(DEFAULT_ZONE_OFFSET));
    }

}
