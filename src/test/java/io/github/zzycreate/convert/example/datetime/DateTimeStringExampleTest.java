package io.github.zzycreate.convert.example.datetime;

import io.github.zzycreate.example.datetime.DateTimeStringExample;
import org.junit.Assert;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_TIMESTAMP_LONG;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_USER_DATE_TIME;
import static io.github.zzycreate.example.constant.DateTimeConstant.USER_PATTERN;

/**
 * @author zzycreate
 * @date 2019/5/10
 */
public class DateTimeStringExampleTest {

    @Test
    public void testToDate() {
        Date date = DateTimeStringExample.toDate(DEFAULT_USER_DATE_TIME, USER_PATTERN);
        Assert.assertEquals(Long.valueOf(DEFAULT_TIMESTAMP_LONG),
                Optional.ofNullable(date).map(Date::getTime).orElse(0L));

    }

    @Test
    public void testToDateWithJdk8() {
        Date date = DateTimeStringExample.toDateWithLocalDateTime(DEFAULT_USER_DATE_TIME, DateTimeFormatter.ofPattern(USER_PATTERN));
        Assert.assertEquals(DEFAULT_TIMESTAMP_LONG, date.getTime());
    }

}
