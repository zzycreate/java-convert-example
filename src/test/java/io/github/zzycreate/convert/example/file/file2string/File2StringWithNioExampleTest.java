package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithNioExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.file.Constant.READER_CONTENT;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithNioExampleTest {

    @Test
    public void testReadByJava7FilesInReadAllBytes() {
        String content = File2StringWithNioExample.readByJava7FilesInReadAllBytes();
        Assert.assertEquals(READER_CONTENT, content);
    }

    @Test
    public void testReadLinesByJava7FilesInReadLines() {
        String content = File2StringWithNioExample.readLinesByJava7FilesInReadLines();
        Assert.assertEquals(READER_CONTENT, content);
    }

}
