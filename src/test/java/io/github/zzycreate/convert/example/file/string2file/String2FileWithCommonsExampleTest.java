package io.github.zzycreate.convert.example.file.string2file;

import io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.file.Constant.CONTENT;
import static io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample.FILE_NAME;

/**
 * @author zzycreate
 * @date 19-5-5
 */
public class String2FileWithCommonsExampleTest {

    @Test
    public void testWriteWithCommons() {

        String2FileWithCommonsExample.writeWithCommons();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            Assert.assertEquals(CONTENT, text);
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
