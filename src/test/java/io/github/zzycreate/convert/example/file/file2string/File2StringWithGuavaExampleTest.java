package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithGuavaExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.file.Constant.LINE1;
import static io.github.zzycreate.example.file.Constant.LINE2;
import static io.github.zzycreate.example.file.Constant.READER_CONTENT;
import static io.github.zzycreate.example.file.Constant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-8
 */
public class File2StringWithGuavaExampleTest {

    @Test
    public void testReadByGuavaSources() {
        String content = File2StringWithGuavaExample.readByGuavaSources();
        Assert.assertEquals(READER_CONTENT, content);
    }

    @Test
    public void testReadLinesByGuava() {
        String content = File2StringWithGuavaExample.readLinesByGuava();
        Assert.assertEquals(LINE1 + SEPARATOR + LINE2, content);
    }

}
