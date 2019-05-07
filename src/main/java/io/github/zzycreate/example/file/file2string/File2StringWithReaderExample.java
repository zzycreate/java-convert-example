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
         // new BufferedReader(new FileReader(FILE_NAME_INPUT));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME_INPUT), StandardCharsets.UTF_8))) {
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

}
