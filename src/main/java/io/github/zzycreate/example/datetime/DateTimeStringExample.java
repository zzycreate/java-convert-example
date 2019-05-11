package io.github.zzycreate.example.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
