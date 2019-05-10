package io.github.zzycreate.convert.example.datetime;

import io.github.zzycreate.example.datetime.DateExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_DATE;
import static io.github.zzycreate.example.constant.DateTimeConstant.DEFAULT_USER_DATE_TIME;

/**
 * @author zzycreate
 * @date 19-5-10
 */
public class DateExampleTest {

    @Test
    public void testDateToString() {
        Assert.assertEquals(DEFAULT_USER_DATE_TIME, DateExample.toString(DEFAULT_DATE, "yyyy-MM-dd hh:mm:ss"));
    }

}
