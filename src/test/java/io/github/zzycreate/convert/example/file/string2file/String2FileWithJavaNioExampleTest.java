package io.github.zzycreate.convert.example.file.string2file;

import io.github.zzycreate.example.file.string2file.String2FileWithJavaNioExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.file.Constant.WRITER_CONTENT;
import static io.github.zzycreate.example.file.string2file.String2FileWithJavaNioExample.FILE_NAME_BY_CHANNEL1;
import static io.github.zzycreate.example.file.string2file.String2FileWithJavaNioExample.FILE_NAME_BY_CHANNEL2;
import static io.github.zzycreate.example.file.string2file.String2FileWithJavaNioExample.FILE_NAME_BY_FILES;

/**
 * @author zzycreate
 * @date 19-5-4
 */
public class String2FileWithJavaNioExampleTest {

    @Test
    public void testWriteByJava7Files(){

        String2FileWithJavaNioExample.writeByJava7Files();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME_BY_FILES)));
            Assert.assertEquals(WRITER_CONTENT, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME_BY_FILES);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

    @Test
    public void testWriteByNioFileChannelViaFileOutputStream() {
        String2FileWithJavaNioExample.writeByNioFileChannelViaFileOutputStream();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME_BY_CHANNEL1)));
            Assert.assertEquals(WRITER_CONTENT, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME_BY_CHANNEL1);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

    @Test
    public void testWriteByNioFileChannelViaRandomAccessFile() {
        String2FileWithJavaNioExample.writeByNioFileChannelViaRandomAccessFile();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME_BY_CHANNEL2)));
            Assert.assertEquals(WRITER_CONTENT, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME_BY_CHANNEL2);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

}
