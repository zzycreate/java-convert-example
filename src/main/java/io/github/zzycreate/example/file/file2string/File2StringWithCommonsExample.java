package io.github.zzycreate.example.file.file2string;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static io.github.zzycreate.example.file.Constant.FILE_NAME_INPUT;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithCommonsExample {

    /**
     * 使用 IOUtils.copy 直接将 Reader/InputStream 转换为 Writer/OutputStream
     *
     * @return 文件内容
     */
    public static String readByIoUtilsCopy() {
        try (FileReader fileReader = new FileReader(FILE_NAME_INPUT);
             StringWriter stringWriter = new StringWriter()) {

            IOUtils.copy(fileReader, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 FileUtils 的 readFileToString 直接读取文件内容，可以设置字符编码
     *
     * @return 文件内容
     */
    public static String readByFileUtilsReadFileToString() {
        try {
            return FileUtils.readFileToString(new File(FILE_NAME_INPUT), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
