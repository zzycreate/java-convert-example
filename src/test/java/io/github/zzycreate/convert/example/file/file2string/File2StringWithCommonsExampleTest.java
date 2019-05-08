package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithCommonsExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.file.Constant.READER_CONTENT;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithCommonsExampleTest {

    @Test
    public void testReadByIoUtilsCopy() {
        String content = File2StringWithCommonsExample.readByIoUtilsCopy();
        Assert.assertEquals(READER_CONTENT, content);
    }

    @Test
    public void testReadByFileUtilsReadFileToString() {
        String content = File2StringWithCommonsExample.readByFileUtilsReadFileToString();
        Assert.assertEquals(READER_CONTENT, content);
    }

}
