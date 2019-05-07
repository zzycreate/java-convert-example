package io.github.zzycreate.convert.example.file.file2string;

import io.github.zzycreate.example.file.file2string.File2StringWithReaderExample;
import org.junit.Assert;
import org.junit.Test;

import static io.github.zzycreate.example.file.Constant.READER_CONTENT;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithReaderExampleTest {

    @Test
    public void testReadByBufferedReader() {
        String content = File2StringWithReaderExample.readByBufferedReader();
        Assert.assertEquals(READER_CONTENT, content);
    }

}
