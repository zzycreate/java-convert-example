package io.github.zzycreate.example.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ZONE_OFFSET;

/**
 * jdk 1.1 和 jdk8 时间相互转换
 */
public class DateLocalDateTimeConvertExample {

    public static Instant date2Instant(Date date){
        return date.toInstant();
    }

    public static Instant date2Instant1(Date date){
        return Instant.ofEpochMilli(date.getTime());
    }

    public static Date instant2Date(Instant instant) {
        return new Date(instant.toEpochMilli());
    }

    public static LocalDateTime date2LocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate date2LocalDate(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime date2LocalTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime){
        return new Date(localDateTime.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli());
    }

}
