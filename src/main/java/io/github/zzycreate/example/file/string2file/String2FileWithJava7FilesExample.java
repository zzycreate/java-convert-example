package io.github.zzycreate.example.file.string2file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 向文件中写入内容
 * File to String
 * <p>
 * 使用 java7 的 nio 类 Files 进行文件写入
 * <p>
 * 由于 nio 是 java7 新增的内容，使用本方法需要首先将程序的 jdk 升级到 jdk7+
 * 1. 如果写入的是字符数据，则需要设置字符编码 {@link java.nio.charset.StandardCharsets} {@link Charset}
 * 2. 如果写入的是字节数据（byte[]），则不需要
 * 3. 如果想在已存在的文件后追加内容，可以增加 {@link java.nio.file.StandardOpenOption#APPEND} 参数
 *
 * @author zzycreate
 * @date 19-5-4
 */
public class String2FileWithJava7FilesExample {

    public static final String FILE_NAME = "useByJava7Files.txt";
    public static final String LINE1 = "The first line";
    public static final String LINE2 = "The second line";
    public static final String SEPARATOR = System.getProperty("line.separator");

    public static void useByJava7Files() {
        List<String> lines = Arrays.asList(LINE1, LINE2);
        Path path = Paths.get(FILE_NAME);
        try {
            Files.write(path, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
