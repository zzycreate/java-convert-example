package io.github.zzycreate.convert.example.file.string2file;

import io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample.FILE_NAME;
import static io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample.LINE1;
import static io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample.LINE2;
import static io.github.zzycreate.example.file.string2file.String2FileWithCommonsExample.SEPARATOR;

/**
 * @author esp
 * @date 19-5-5
 */
public class String2FileWithCommonsExampleTest {

    @Test
    public void testUseWithCommons() {

        String2FileWithCommonsExample.useWithCommons();

        try {
            String text = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            Assert.assertEquals(LINE1 + SEPARATOR + LINE2 + SEPARATOR, text);
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
