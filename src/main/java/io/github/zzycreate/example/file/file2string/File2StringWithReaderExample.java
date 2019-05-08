package io.github.zzycreate.example.file.file2string;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static io.github.zzycreate.example.file.Constant.FILE_NAME_INPUT;
import static io.github.zzycreate.example.file.Constant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-7
 */
public class File2StringWithReaderExample {

    /**
     * 使用 BufferedReader 读取数据
     *
     * @return 文件数据
     */
    public static String readByBufferedReader() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_NAME_INPUT), StandardCharsets.UTF_8))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(SEPARATOR);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 直接使用 FileInputStream 写入 byte[] 缓存，这段代码只需要 jdk1.0+
     *
     * @return 文件文本
     */
    public static String readByFileInputStream() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FILE_NAME_INPUT);
            byte[] buffer = new byte[fis.available()];
            int length = fis.read(buffer);
            return new String(buffer, 0, length, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
