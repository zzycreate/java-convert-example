package io.github.zzycreate.convert.example.file.file2file;

import io.github.zzycreate.example.file.file2file.File2FileWithNioExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.constant.FileConstant.CONTENT;
import static io.github.zzycreate.example.file.file2file.File2FileWithNioExample.FILE_NAME_OUTPUT;

/**
 * @author zzycreate
 * @date 19-5-6
 */
public class File2FileWithNioExampleTest {

    @Test
    public void testCopeWithChannel() {
        File2FileWithNioExample.copeWithChannel();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME_OUTPUT)));
            Assert.assertEquals(CONTENT, text);
        } catch (IOException e) {
            Assert.assertNull(e);
        }
        // delete file
        File file = new File(FILE_NAME_OUTPUT);
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());
    }

}
