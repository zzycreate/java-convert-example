package io.github.zzycreate.convert.example.file.string2file;

import io.github.zzycreate.example.file.string2file.String2FileWithWriterExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.FILE_NAME1;
import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.FILE_NAME2;
import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.FILE_NAME3;
import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.FILE_NAME4;
import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.LINE1;
import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.LINE2;
import static io.github.zzycreate.example.file.string2file.String2FileWithWriterExample.SEPARATOR;

/**
 * String2FileWithWriterExample 单元测试
 *
 * @author zzycreate
 * @date 19-5-3
 */
public class String2FileWithWriterExampleTest {

    @Test
    public void usePrintWriter() {

        String2FileWithWriterExample.usePrintWriterInTryCatchFinally();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME1)));
            Assert.assertEquals(LINE1 + SEPARATOR + LINE2 + SEPARATOR, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file1 = new File(FILE_NAME1);
        Assert.assertNotNull(file1);
        Assert.assertTrue(file1.exists());
        Assert.assertTrue(file1.delete());
        Assert.assertFalse(file1.exists());

        String2FileWithWriterExample.usePrintWriterInTryWithResources();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME2)));
            Assert.assertEquals(LINE1 + SEPARATOR + LINE2 + SEPARATOR, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file2 = new File(FILE_NAME2);
        Assert.assertNotNull(file2);
        Assert.assertTrue(file2.exists());
        Assert.assertTrue(file2.delete());
        Assert.assertFalse(file2.exists());

    }

    @Test
    public void useFileWriter() {

        String2FileWithWriterExample.useFileWriter();

        String content = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME3)));
            Assert.assertEquals(content, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }

        String2FileWithWriterExample.useFileWriter();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME3)));
            Assert.assertEquals(content + content, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }

        // delete file
        File file = new File(FILE_NAME3);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

    @Test
    public void useBufferdWriter() {

        String2FileWithWriterExample.useBufferdWriter();

        String content = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME4)));
            Assert.assertEquals(content, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }

        String2FileWithWriterExample.useBufferdWriter();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME4)));
            Assert.assertEquals(content + content, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }

        // delete file
        File file = new File(FILE_NAME4);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

}
