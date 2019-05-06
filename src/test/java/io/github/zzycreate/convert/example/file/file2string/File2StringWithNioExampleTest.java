package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithNioExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.file.Constant.LINE1;
import static io.github.zzycreate.example.file.Constant.LINE2;
import static io.github.zzycreate.example.file.Constant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithNioExampleTest {

    private static final String CONTENT = LINE1 + SEPARATOR + LINE2 + SEPARATOR;

    @Test
    public void testReadByJava7FilesInReadAllBytes() {
        String content = File2StringWithNioExample.readByJava7FilesInReadAllBytes();
        Assert.assertEquals(CONTENT, content);
    }

    @Test
    public void testReadLinesByJava7FilesInReadLines() {
        String content = File2StringWithNioExample.readLinesByJava7FilesInReadLines();
        Assert.assertEquals(CONTENT, content);
    }

}
