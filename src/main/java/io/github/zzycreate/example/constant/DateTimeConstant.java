package io.github.zzycreate.example.constant;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 时间常量
 *
 * @author zzycreate
 * @date 19-5-10
 */
public interface DateTimeConstant {

    String DEFAULT_YEAR_STR = "2019";
    int DEFAULT_YEAR = 2019;
    String DEFAULT_MONTH_STR = "05";
    int DEFAULT_MONTH = 5;
    String DEFAULT_DAY_STR = "10";
    int DEFAULT_DAY = 10;
    String DEFAULT_HOUR_STR = "13";
    int DEFAULT_HOUR = 13;
    String DEFAULT_MINUTE_STR = "14";
    int DEFAULT_MINUTE = 14;
    String DEFAULT_SECOND_STR = "15";
    int DEFAULT_SECOND = 15;

    String DEFAULT_DATE_STR = "2019-05-10";
    String DEFAULT_TIME_STR = "13:14:15";
    String DEFAULT_ZONE_ID_STR = "Asia/Shanghai";
    String DEFAULT_ZONE_OFFSET_STR = "+08:00";
    String USER_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认示例的 LocalDateTime
     */
    LocalDateTime DEFAULT_LOCAL_DATE_TIME = LocalDateTime.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_HOUR,
            DEFAULT_MINUTE, DEFAULT_SECOND);
    /**
     * 默认时区：东八区
     */
    ZoneId DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_ZONE_ID_STR);
    /**
     * 默认时区偏移量：+08:00
     */
    ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of(DEFAULT_ZONE_OFFSET_STR);
    /**
     * 默认时区（东八区）的ZonedDateTime
     */
    ZonedDateTime DEFAULT_ZONED_DATE_TIME = DEFAULT_LOCAL_DATE_TIME.atZone(DEFAULT_ZONE_ID);
    /**
     * 默认上海时区（东八区+08：00）的Instant
     */
    Instant DEFAULT_INSTANT = DEFAULT_ZONED_DATE_TIME.toInstant();
    /**
     * 默认Instant中获取的Date对象，由于Date没有时区概念，因此默认Date不带时区
     */
    Date DEFAULT_DATE = Date.from(DEFAULT_INSTANT);
    /**
     * 带时区概念的时间戳
     */
    Timestamp DEFAULT_INSTANT_TIMESTAMP = Timestamp.from(DEFAULT_INSTANT);
    /**
     * 不带时区改练的时间戳
     */
    Timestamp DEFAULT_TIMESTAMP = Timestamp.valueOf(DEFAULT_LOCAL_DATE_TIME);
    /**
     * 带时区概念的时间戳毫秒值
     */
    long DEFAULT_INSTANT_TIMESTAMP_LONG = DEFAULT_INSTANT_TIMESTAMP.getTime();
    /**
     * 不带时区的时间戳毫秒值
     */
    long DEFAULT_TIMESTAMP_LONG = DEFAULT_TIMESTAMP.getTime();

    String DEFAULT_ISO_LOCAL_DATE_STR = "2019-05-10";
    String DEFAULT_ISO_OFFSET_DATE_STR = "2019-05-10+08:00";
    String DEFAULT_ISO_DATE_STR = "2019-05-10+08:00";
    String DEFAULT_ISO_LOCAL_TIME_STR = "13:14:15";
    String DEFAULT_ISO_OFFSET_TIME_STR = "13:14:15+08:00";
    String DEFAULT_ISO_TIME_STR = "13:14:15+08:00";
    String DEFAULT_ISO_LOCAL_DATE_TIME_STR = "2019-05-10T13:14:15";
    String DEFAULT_ISO_OFFSET_DATE_TIME_STR = "2019-05-10T13:14:15+08:00";
    String DEFAULT_ISO_ZONED_DATE_TIME_STR = "2019-05-10T13:14:15+08:00[Asia/Shanghai]";
    String DEFAULT_ISO_DATE_TIME_STR = "2019-05-10T13:14:15+08:00[Asia/Shanghai]";
    String DEFAULT_ISO_ORDINAL_DATE_STR = "2019-130+08:00";
    String DEFAULT_ISO_WEEK_DATE_STR = "2019-W19-5+08:00";
    String DEFAULT_ISO_INSTANT_STR = "2019-05-10T05:14:15Z";
    String DEFAULT_BASIC_ISO_DATE_STR = "20190510+0800";
    String DEFAULT_RFC_1123_DATE_TIME_STR = "Fri, 10 May 2019 13:14:15 +0800";
    String DEFAULT_USER_DATE_TIME = "2019-05-10 13:14:15";

}
