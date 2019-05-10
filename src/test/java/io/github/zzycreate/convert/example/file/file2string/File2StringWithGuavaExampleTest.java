package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithGuavaExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.constant.FileConstant.CONTENT;
import static io.github.zzycreate.example.constant.FileConstant.LINE1;
import static io.github.zzycreate.example.constant.FileConstant.LINE2;
import static io.github.zzycreate.example.constant.FileConstant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-8
 */
public class File2StringWithGuavaExampleTest {

    @Test
    public void testReadByGuavaSources() {
        String content = File2StringWithGuavaExample.readByGuavaSources();
        Assert.assertEquals(CONTENT, content);
    }

    @Test
    public void testReadLinesByGuava() {
        String content = File2StringWithGuavaExample.readLinesByGuava();
        Assert.assertEquals(LINE1 + SEPARATOR + LINE2, content);
    }

}
