package io.github.zzycreate.convert.example.datetime;

import io.github.zzycreate.example.datetime.DateLocalDateTimeConvertExample;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static io.github.zzycreate.example.constant.DateTimeConstant.*;

public class DateLocalDateTimeConvertExampleTest {

    @Test
    public void date2InstantTest(){
        Instant instant = DateLocalDateTimeConvertExample.date2Instant(DEFAULT_DATE);
        Assert.assertEquals(DEFAULT_INSTANT, instant);
        Instant instant1 = DateLocalDateTimeConvertExample.date2Instant1(DEFAULT_DATE);
        Assert.assertEquals(DEFAULT_INSTANT, instant1);
    }

    @Test
    public void instant2DateTest(){
        Date date = DateLocalDateTimeConvertExample.instant2Date(DEFAULT_INSTANT);
        Assert.assertEquals(DEFAULT_DATE, date);
    }

    @Test
    public void date2LocalDateTime(){
        LocalDateTime dateTime = DateLocalDateTimeConvertExample.date2LocalDateTime(DEFAULT_DATE);
        Assert.assertEquals(DEFAULT_LOCAL_DATE_TIME, dateTime);
        LocalDate date = DateLocalDateTimeConvertExample.date2LocalDate(DEFAULT_DATE);
        Assert.assertEquals(DEFAULT_LOCAL_DATE, date);
        LocalTime time = DateLocalDateTimeConvertExample.date2LocalTime(DEFAULT_DATE);
        Assert.assertEquals(DEFAULT_LOCAL_TIME, time);
    }

    @Test
    public void localDateTime2Date() {
        Date date = DateLocalDateTimeConvertExample.localDateTime2Date(DEFAULT_LOCAL_DATE_TIME);
        Assert.assertEquals(DEFAULT_DATE, date);
    }

}
