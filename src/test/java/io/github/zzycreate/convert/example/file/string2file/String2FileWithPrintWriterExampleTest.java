package io.github.zzycreate.convert.example.file.string2file;

import io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.FILE_NAME1;
import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.FILE_NAME2;
import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.FILE_NAME3;
import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.FILE_NAME4;
import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.LINE1;
import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.LINE2;
import static io.github.zzycreate.example.file.string2file.String2FileWithPrintWriterExample.SEPARATOR;

/**
 * String2FileWithPrintWriterExample 单元测试
 *
 * @author zzycreate
 * @date 19-5-3
 */
public class String2FileWithPrintWriterExampleTest {

    @Test
    public void testInTryCatchFinally() {

        String2FileWithPrintWriterExample.useInTryCatchFinally();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME1)));
            Assert.assertEquals(LINE1 + SEPARATOR + LINE2 + SEPARATOR, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME1);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

    @Test
    public void testInTryWithResources() {

        String2FileWithPrintWriterExample.useInTryWithResources();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME2)));
            Assert.assertEquals(LINE1 + SEPARATOR + LINE2 + SEPARATOR, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME2);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());

    }

    @Test
    public void useFileWriter() {

        String2FileWithPrintWriterExample.useFileWriter();

        String content = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME3)));
            Assert.assertEquals(content, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }

        String2FileWithPrintWriterExample.useFileWriter();

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

        String2FileWithPrintWriterExample.useBufferdWriter();

        String content = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME4)));
            Assert.assertEquals(content, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }

        String2FileWithPrintWriterExample.useBufferdWriter();

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
