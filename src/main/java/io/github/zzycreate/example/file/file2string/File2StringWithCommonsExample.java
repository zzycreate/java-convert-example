package io.github.zzycreate.example.file.file2string;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

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

}
