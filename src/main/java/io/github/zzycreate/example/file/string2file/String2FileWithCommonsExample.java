package io.github.zzycreate.example.file.string2file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.github.zzycreate.example.constant.FileConstant.CONTENT;

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
 * @see <a href="https://stackoverflow.com/questions/2885173">stackoverflow.com/a/2885173</a>
 */
public class String2FileWithCommonsExample {

    public static final String FILE_NAME = "writeWithCommons.txt";

    /**
     * 使用FileUtils可以进行文件写入, 重构的方法可以设置文件编码和写入模式
     */
    public static void writeWithCommons() {
        try {
            FileUtils.writeStringToFile(new File(FILE_NAME), CONTENT, StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
