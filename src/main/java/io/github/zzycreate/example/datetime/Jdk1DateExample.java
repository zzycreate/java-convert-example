package io.github.zzycreate.example.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * JDK1.0 时间日期类型转换
 * JDK1.0 的时间日期类型：{@link java.util.Date}  {@link java.util.Calendar} {@link java.util.GregorianCalendar}
 * {@link java.sql.Date} {@link java.sql.Time} {@link java.sql.Timestamp}
 *
 * @author zzycreate
 */
public class Jdk1DateExample {

    public void create() {
        // 毫秒值
        long timestamp = System.currentTimeMillis();

        // java.util.Date 除了一下两种构造方法，其他构造方法均已过期
        Date date = new Date();
        Date date1 = new Date(timestamp);

        // sql date/time/timestamp 是针对SQL语言使用的，Date只有日期而没有时间，Time只有时间而没有日期
        java.sql.Date sqlDate = new java.sql.Date(timestamp);
        java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
        java.sql.Time sqlTime = new java.sql.Time(timestamp);
        java.sql.Time sqlTime1 = new java.sql.Time(date1.getTime());
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(timestamp);
        java.sql.Timestamp sqlTimestamp1 = new java.sql.Timestamp(date1.getTime());

        // Calendar 代表日历, GregorianCalendar 代表公历
        Calendar calendar = Calendar.getInstance();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
    }

    public void getValue(){
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        java.sql.Date sqlDate = new java.sql.Date(timestamp);
        java.sql.Time sqlTime = new java.sql.Time(timestamp);
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(timestamp);
        Calendar calendar = Calendar.getInstance();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        // Date 除了 getTime() 方法可以获取时间戳毫秒值外，其他 get 方法在 jdk8 中均已过期，不推荐时间
        // sql date/time/timestamp 均为 util date 的子类，过期方法类似
        long s = date.getTime();
        long sqlTimes = sqlDate.getTime();
        long sqlTime1 = sqlTime.getTime();
        long sqlTime2 = sqlTimestamp.getTime();
        long sqlNano1 = sqlTimestamp.getNanos();
        long sqlNano2 = sqlTimestamp.getNanos();

        Date calendarTime = calendar.getTime();
        int year = calendar.get(Calendar.YEAR);
        // 由于月份是从0开始，一般 +1 之后才和人的主观感受一直
        int month = calendar.get(Calendar.MONTH) + 1;
        int dateNum = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        // 星期
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 日期
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        boolean isLeapYear = gregorianCalendar.isLeapYear(year);
    }

}
