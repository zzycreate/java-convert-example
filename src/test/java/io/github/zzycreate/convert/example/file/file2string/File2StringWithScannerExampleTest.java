package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithScannerExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.constant.FileConstant.CONTENT;

/**
 * @author zzycreate
 * @date 19-5-8
 */
public class File2StringWithScannerExampleTest {

    @Test
    public void testReadByScanner() {
        String content = File2StringWithScannerExample.readByScanner();
        Assert.assertEquals(CONTENT, content);
    }

}
