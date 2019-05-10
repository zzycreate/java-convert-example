package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithReaderExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.constant.FileConstant.CONTENT;
import static io.github.zzycreate.example.constant.FileConstant.LINE1;
import static io.github.zzycreate.example.constant.FileConstant.LINE2;
import static io.github.zzycreate.example.constant.FileConstant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithReaderExampleTest {

    @Test
    public void testReadByBufferedReader() {
        String content = File2StringWithReaderExample.readByBufferedReader();
        Assert.assertEquals(CONTENT, content);
    }

    @Test
    public void testReadLinesByBufferedReader() {
        String content = File2StringWithReaderExample.readLinesByBufferedReader();
        Assert.assertEquals(LINE1 + SEPARATOR + LINE2, content);
    }

    @Test
    public void testReadByFileInputStream() {
        String content = File2StringWithReaderExample.readByFileInputStream();
        Assert.assertEquals(CONTENT, content);
    }

}
