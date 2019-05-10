package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithNioExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.constant.FileConstant.CONTENT;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithNioExampleTest {

    @Test
    public void testReadByJava7FilesInReadAllBytes() {
        String content = File2StringWithNioExample.readByJava7FilesInReadAllBytes();
        Assert.assertEquals(CONTENT, content);
    }

    @Test
    public void testReadLinesByJava7FilesInReadAllLines() {
        String content = File2StringWithNioExample.readLinesByJava7FilesInReadAllLines();
        Assert.assertEquals(CONTENT, content);
    }

    @Test
    public void testReadLinesByJava7FilesInLines() {
        String content = File2StringWithNioExample.readLinesByJava7FilesInLines();
        Assert.assertEquals(CONTENT, content);
    }

}
