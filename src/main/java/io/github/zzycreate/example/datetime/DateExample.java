package io.github.zzycreate.example.datetime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ZONE_ID;

/**
 * java.util.Date
 *
 * @author zzycreate
 * @date 19-5-10
 */
public class DateExample {

    /**
     * java.util.Date -> String
     * 格式化日期
     * 使用 SimpleDateFormat 进行日期格式化
     * <p>
     * G - 纪元标记 （eg: AD)
     * y - 四位年份 (eg: 2001)
     * M - 月份 (eg: July or 07)
     * d - 一个月的日期 (eg: 10)
     * h -  A.M./P.M. (1~12)格式小时 (eg: 12)
     * H - 一天中的小时 (0~23) (eg: 22)
     * m - 分钟数 (eg: 30)
     * s - 秒数 (eg: 55)
     * S - 毫秒数 (eg: 234)
     * E - 星期几 (eg: Tuesday)
     * D - 一年中的日子 (eg: 360)
     * F - 一个月中第几周的周几 (eg: 2 (second Wed. in July))
     * w - 一年中第几周 (eg: 40)
     * W - 一个月中第几周 (eg: 1)
     * a - A.M./P.M. 标记 (eg: PM)
     * k - 一天中的小时(1~24) (eg: 24)
     * K -  A.M./P.M. (0~11)格式小时 (eg: 10)
     * z - 时区 (eg: Eastern Standard Time)
     * ' - 文字定界符 (eg: Delimiter)
     * " - 单引号 (eg: `)
     *
     * @param date    jdk1.1 Date
     * @param pattern 转换的格式
     * @return 日期时间文本
     */
    public static String toString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * java.util.Date -> String
     * 使用 jdk8 的 DateTimeFormatter 进行日期格式化
     * java.util.Date 需要转换为 java.time.ZonedDateTime 才能使用 DateTimeFormatter 格式化时间
     *
     * @param date              jdk1.1 Date
     * @param dateTimeFormatter jdk8 日期时间格式化对象
     * @return 日期时间文本
     */
    public static String toString(Date date, DateTimeFormatter dateTimeFormatter) {
        ZonedDateTime zonedDateTime = date.toInstant().atZone(DEFAULT_ZONE_ID);
        return zonedDateTime.format(dateTimeFormatter);
    }

    /**
     * java.util.Date -> long
     *
     * @param date jdk1.1 Date
     * @return 时间戳毫秒数
     */
    public static long toTimestamp(Date date) {
        return date.getTime();
    }

    /**
     * java.util.Date -> java.sql.Timestamp
     *
     * @param date jdk1.1 Date
     * @return sql 时间戳对象
     */
    public static Timestamp toSqlTimstamp(Date date) {
        return new Timestamp(date.getTime());
    }

}
