package io.github.zzycreate.convert.example.datetime;

import io.github.zzycreate.example.datetime.DateExample;
import org.junit.Assert;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_BASIC_ISO_DATE_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_DATE;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_DATE_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_DATE_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_INSTANT_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_LOCAL_DATE_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_LOCAL_DATE_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_LOCAL_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_OFFSET_DATE_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_OFFSET_DATE_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_OFFSET_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_ORDINAL_DATE_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_WEEK_DATE_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_ISO_ZONED_DATE_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_RFC_1123_DATE_TIME_STR;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_USER_DATE_TIME;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_USER_PATTERN;

/**
 * @author zzycreate
 * @date 19-5-10
 */
public class DateExampleTest {

    @Test
    public void testDateToString() {
        // use SimpleDateFormat
        Assert.assertEquals(DEFAULT_USER_DATE_TIME,
                DateExample.toString(DEFAULT_DATE, DEFAULT_USER_PATTERN));

        // use DateTimeFormatter
        Assert.assertEquals(DEFAULT_USER_DATE_TIME,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ofPattern(DEFAULT_USER_PATTERN)));
        Assert.assertEquals(DEFAULT_ISO_LOCAL_DATE_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_LOCAL_DATE));
        Assert.assertEquals(DEFAULT_ISO_OFFSET_DATE_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_OFFSET_DATE));
        Assert.assertEquals(DEFAULT_ISO_DATE_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_DATE));
        Assert.assertEquals(DEFAULT_ISO_LOCAL_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_LOCAL_TIME));
        Assert.assertEquals(DEFAULT_ISO_OFFSET_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_OFFSET_TIME));
        Assert.assertEquals(DEFAULT_ISO_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_TIME));
        Assert.assertEquals(DEFAULT_ISO_LOCAL_DATE_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Assert.assertEquals(DEFAULT_ISO_OFFSET_DATE_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        Assert.assertEquals(DEFAULT_ISO_ZONED_DATE_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_ZONED_DATE_TIME));
        Assert.assertEquals(DEFAULT_ISO_DATE_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_DATE_TIME));
        Assert.assertEquals(DEFAULT_ISO_ORDINAL_DATE_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_ORDINAL_DATE));
        Assert.assertEquals(DEFAULT_ISO_WEEK_DATE_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_WEEK_DATE));
        Assert.assertEquals(DEFAULT_ISO_INSTANT_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.ISO_INSTANT));
        Assert.assertEquals(DEFAULT_BASIC_ISO_DATE_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.BASIC_ISO_DATE));
        Assert.assertEquals(DEFAULT_RFC_1123_DATE_TIME_STR,
                DateExample.toString(DEFAULT_DATE, DateTimeFormatter.RFC_1123_DATE_TIME));
    }

}
