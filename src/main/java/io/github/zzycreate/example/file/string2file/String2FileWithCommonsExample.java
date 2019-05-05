package io.github.zzycreate.example.file.string2file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 向文件中写入内容
 * File to String
 * <p>
 * 使用 Commons-IO 进行文件写入
 * <p>
 * Commons-io 是 apache 的 Commons 系列项目中用于处理 IO 的工具包。包中提供很多方便的文件操作操作API
 * Commons-io 项目地址： https://commons.apache.org/proper/commons-io/
 *
 * @author zzycreate
 * @date 19-5-4
 * @see <a href="http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java">
 * http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java</a>
 */
public class String2FileWithCommonsExample {

    public static final String FILE_NAME = "useByCommons.txt";
    public static final String LINE1 = "The first line";
    public static final String LINE2 = "The second line";
    public static final String SEPARATOR = System.getProperty("line.separator");

    /**
     * 使用FileUtils可以进行文件写入, 重构的方法可以设置文件编码和写入模式
     */
    public static void useWithCommons() {
        String data = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            FileUtils.writeStringToFile(new File(FILE_NAME), data, StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
