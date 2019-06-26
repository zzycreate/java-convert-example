package io.github.zzycreate.example.datetime;

import java.time.*;
import java.util.Date;

import static io.github.zzycreate.example.constant.DateTimeConstant.*;

/**
 * JDK8 的时间类型转换
 * JDK8 的时间类型： {@link java.time.LocalTime} {@link java.time.LocalDate} {@link java.time.LocalDateTime}
 * {@link java.time.ZoneId} {@link java.time.ZoneOffset} {@link java.time.ZonedDateTime}
 * {@link java.time.Instant} {@link java.time.Duration}
 * <p>
 * LocalTime: 本地时间
 * LocalDate: 本地日期
 * LocalDateTime: 本地日期时间 = LocalDate + LocalTime
 * ZonedDateTime: 分区日期时间 = LocalDateTime + ZoneId/ZoneOffset
 * Instant: 时间点/时间戳 = seconds + nano
 * Duration: 一段时间(时分秒)
 * Period: 一段时间(年月日)
 *
 * @author zzycreate
 */
public class Jdk8TimeExample {

    public static void create() {
        LocalTime time = LocalTime.now();
        LocalTime time1 = LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTE, DEFAULT_SECOND);
        LocalTime time2 = LocalTime.ofSecondOfDay(DEFAULT_SECOND_OF_DAY);
        LocalTime time3 = LocalTime.parse(DEFAULT_TIME_STR);

        LocalDate date = LocalDate.now();
        LocalDate date1 = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
        LocalDate date2 = LocalDate.ofYearDay(DEFAULT_YEAR, DFFAULT_DAY_OF_YEAR);
        LocalDate date3 = LocalDate.parse(DEFAULT_DATE_STR);

        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTime1 = LocalDateTime.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY,
                DEFAULT_HOUR, DEFAULT_MINUTE, DEFAULT_SECOND);
        LocalDateTime dateTime2 = LocalDateTime.of(date, time);
        LocalDateTime dateTime3 = date.atStartOfDay();
        LocalDateTime dateTime4 = date.atTime(time);
        LocalDateTime dateTime5 = date.atTime(DEFAULT_HOUR, DEFAULT_MINUTE, DEFAULT_SECOND);
        LocalDateTime dateTime6 = time.atDate(date);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime1 = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = dateTime.atZone(ZoneId.of(DEFAULT_ZONE_ID_STR));

        LocalDateTime dateTime7 = zonedDateTime.toLocalDateTime();
        LocalDate date4 = zonedDateTime.toLocalDate();
        LocalTime time4 = zonedDateTime.toLocalTime();

        Instant instant = dateTime.toInstant(ZoneOffset.of(DEFAULT_ZONE_OFFSET_STR));
        Instant instant1 = zonedDateTime.toInstant();
        Instant instant2 = new Date().toInstant();

        Duration duration = Duration.between(time, time1);
        Period period = Period.between(date, date1);

    }

    public static void getValue(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        LocalDateTime dateTime = zonedDateTime.toLocalDateTime();
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        int year = date.getYear();
        Month month = date.getMonth();
        int monthValue = date.getMonthValue();
        int dayOfYear = date.getDayOfYear();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        int nano = time.getNano();

        ZoneId zone = zonedDateTime.getZone();
        ZoneOffset offset = zonedDateTime.getOffset();

    }

    public static void compare() {
        LocalDateTime dateTime = LocalDateTime.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY,
                DEFAULT_HOUR, DEFAULT_MINUTE, DEFAULT_SECOND);
        LocalDateTime now = LocalDateTime.now();

        System.out.println(String.format("dateTime is before now: %s", dateTime.isBefore(now)));
        System.out.println(String.format("dateTime is before now: %s", dateTime.isAfter(now)));
        System.out.println(String.format("date is leap year: %s", dateTime.toLocalDate().isLeapYear()));
    }

}
