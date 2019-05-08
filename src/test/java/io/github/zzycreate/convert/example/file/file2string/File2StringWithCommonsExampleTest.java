package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithCommonsExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.file.Constant.CONTENT;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithCommonsExampleTest {

    @Test
    public void testReadByIoUtilsCopy() {
        String content = File2StringWithCommonsExample.readByIoUtilsCopy();
        // cope 复制的时候写入的是系统分隔符
        Assert.assertEquals(CONTENT, content);
    }

    @Test
    public void testReadByFileUtilsReadFileToString() {
        String content = File2StringWithCommonsExample.readByFileUtilsReadFileToString();
        // readFileToString 写入的是系统分隔符
        Assert.assertEquals(CONTENT, content);
    }

}
