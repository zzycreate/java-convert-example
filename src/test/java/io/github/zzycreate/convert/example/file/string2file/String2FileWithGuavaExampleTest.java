package io.github.zzycreate.convert.example.file.string2file;

import io.github.zzycreate.example.file.string2file.String2FileWithGuavaExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.file.Constant.WRITER_CONTENT;
import static io.github.zzycreate.example.file.string2file.String2FileWithGuavaExample.FILE_NAME;

/**
 * @author zzycreate
 * @date 19-5-5
 */
public class String2FileWithGuavaExampleTest {

    @Test
    public void testWriteByGuavaFiles() {
        String2FileWithGuavaExample.writeByGuavaFiles();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            Assert.assertEquals(WRITER_CONTENT, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());
    }

    @Test
    public void testWriteByGuavaCharSink(){
        String2FileWithGuavaExample.writeByGuavaCharSink();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            Assert.assertEquals(WRITER_CONTENT, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());
    }

}
