package io.github.zzycreate.example.constant;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    String DEFAULT_HOUR_STR = "12";
    int DEFAULT_HOUR = 12;
    String DEFAULT_MINUTE_STR = "13";
    int DEFAULT_MINUTE = 13;
    String DEFAULT_SECOND_STR = "14";
    int DEFAULT_SECOND = 14;

    String DEFAULT_DATE_STR = "2019-05-10";
    String DEFAULT_TIME_STR = "12:13:14";
    String DEFAULT_ZONE_STR = "Asia/Shanghai";
    String DEFAULT_USER_PATTERN = "yyyy-MM-dd hh:mm:ss";

    LocalDateTime DEFAULT_LOCAL_DATE_TIME = LocalDateTime.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_HOUR,
            DEFAULT_MINUTE, DEFAULT_SECOND);
    ZoneId DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_ZONE_STR);
    ZonedDateTime DEFAULT_ZONED_DATE_TIME = DEFAULT_LOCAL_DATE_TIME.atZone(DEFAULT_ZONE_ID);
    Instant DEFAULT_INSTANT = DEFAULT_ZONED_DATE_TIME.toInstant();

    Date DEFAULT_DATE = Date.from(DEFAULT_INSTANT);
    Timestamp DEFAULT_TIMESTAMP = Timestamp.from(DEFAULT_INSTANT);
    long DEFAULT_TIMESTAMP_LONG = DEFAULT_TIMESTAMP.getTime();

    String DEFAULT_ISO_LOCAL_DATE_STR = "2019-05-10";
    String DEFAULT_ISO_OFFSET_DATE_STR = "2019-05-10+08:00";
    String DEFAULT_ISO_DATE_STR = "2019-05-10+08:00";
    String DEFAULT_ISO_LOCAL_TIME_STR = "12:13:14";
    String DEFAULT_ISO_OFFSET_TIME_STR = "12:13:14+08:00";
    String DEFAULT_ISO_TIME_STR = "12:13:14+08:00";
    String DEFAULT_ISO_LOCAL_DATE_TIME_STR = "2019-05-10T12:13:14";
    String DEFAULT_ISO_OFFSET_DATE_TIME_STR = "2019-05-10T12:13:14+08:00";
    String DEFAULT_ISO_ZONED_DATE_TIME_STR = "2019-05-10T12:13:14+08:00[Asia/Shanghai]";
    String DEFAULT_ISO_DATE_TIME_STR = "2019-05-10T12:13:14+08:00[Asia/Shanghai]";
    String DEFAULT_ISO_ORDINAL_DATE_STR = "2019-130+08:00";
    String DEFAULT_ISO_WEEK_DATE_STR = "2019-W19-5+08:00";
    String DEFAULT_ISO_INSTANT_STR = "2019-05-10T04:13:14Z";
    String DEFAULT_BASIC_ISO_DATE_STR = "20190510+0800";
    String DEFAULT_RFC_1123_DATE_TIME_STR = "Fri, 10 May 2019 12:13:14 +0800";
    String DEFAULT_USER_DATE_TIME = "2019-05-10 12:13:14";

}
