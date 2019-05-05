package io.github.zzycreate.example.file.string2file;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.github.zzycreate.example.file.Constant.LINE1;
import static io.github.zzycreate.example.file.Constant.LINE2;
import static io.github.zzycreate.example.file.Constant.SEPARATOR;

/**
 * 向文件中写入内容
 * File to String
 * <p>
 * 使用 Guava 进行文件写入
 * <p>
 * Guava 是 google 开源的工具包，工具包中包含各种类型的工具，设计精巧
 * Guava 的官方开源项目地址： https://github.com/google/guava
 * <p>
 * {@link com.google.common.io.Files Files} 是 guava 中文件操作的工具类, 类名和 jdk7 nio 的工具类相同，注意包名的不同
 *
 * @author zzycreate
 * @date 19-5-5
 * @see <a href="https://stackoverflow.com/questions/2885173">stackoverflow.com/a/2885173</a>
 */
public class String2FileWithGuavaExample {

    public static final String FILE_NAME = "writeByGuavaFiles.txt";

    /**
     * guava 的 Files 可以直接向文件中写入 byte[] 数据
     * Files 标注为 @Beta 不稳定的版本，并提示使用 jdk7 nio 的 Files、MoreFiles 等工具类利用 nio 的 Path 进行文件处理
     */
    public static void writeByGuavaFiles() {
        String data = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            Files.write(data.getBytes(), new File(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由于 guava Files 的 write 写入 String 的重载方法标注为过期，查看文档可知，推荐使用 CharSink 的 write 方法进行文件写入
     * guava 的 CharSink 可以直接向文件中写入字符数据，ByteSink 则可以写入字节数据
     * Files 中的 write、append、copy 等方法本质上都是调用的这些 Sink 类。
     */
    public static void writeByGuavaCharSink() {
        String data = LINE1 + SEPARATOR + LINE2 + SEPARATOR;
        try {
            Files.asCharSink(new File(FILE_NAME), StandardCharsets.UTF_8, FileWriteMode.APPEND).write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
