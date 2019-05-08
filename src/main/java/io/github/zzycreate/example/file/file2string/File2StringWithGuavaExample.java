package io.github.zzycreate.example.file.file2string;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.github.zzycreate.example.file.Constant.FILE_NAME_INPUT;
import static io.github.zzycreate.example.file.Constant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-8
 */
public class File2StringWithGuavaExample {

    /**
     * 使用 guava 的 Files 转换为 ByteSource/CharSource 然后直接 read 成字符串
     *
     * @return 文本内容
     */
    public static String readByGuavaSources() {
        try {
            /// Resources 可以读取 url
            // com.google.common.io.Resources.asCharSource(new URL(""), StandardCharsets.UTF_8).read();
            return com.google.common.io.Files.asCharSource(new File(FILE_NAME_INPUT), StandardCharsets.UTF_8).read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readLinesByGuava() {
        try {
            return String.join(SEPARATOR, Files.readLines(new File(FILE_NAME_INPUT), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
