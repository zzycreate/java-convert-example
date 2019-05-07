package io.github.zzycreate.example.file.file2string;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static io.github.zzycreate.example.file.Constant.FILE_NAME_INPUT;
import static io.github.zzycreate.example.file.Constant.SEPARATOR;

/**
 * @author zzycreate
 * @date 19-5-5
 * @see <a href="https://stackoverflow.com/questions/326390">stackoverflow.com/a/326390</a>
 * @see <a href="https://stackoverflow.com/questions/4716503">stackoverflow.com/a/4716503</a>
 * @see <a href="https://stackoverflow.com/questions/14169661">stackoverflow.com/a/14169661</a>
 * @see <a href="https://stackoverflow.com/questions/3402735">stackoverflow.com/a/3402735</a>
 */
public class File2StringWithNioExample {

    /**
     * 使用 Files.readAllBytes() 直接读取文件所有的字节
     *
     * @return content data from the file
     */
    public static String readByJava7FilesInReadAllBytes() {
        try {
            return new String(Files.readAllBytes(Paths.get(FILE_NAME_INPUT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 Files.readAllLines() 逐行读取文件内容
     *
     * @return 文件内容
     */
    public static String readLinesByJava7FilesInReadAllLines() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME_INPUT), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            lines.forEach(s -> sb.append(s).append(SEPARATOR));
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 Files.lines() 方法转换为 Stream<String> 进行逐行读取。
     * 由于 Stream 内部无法在 lambda 中使用带有 checked Exception 的方法，因此用try-with-resources 进行异常的抓取
     *
     * @return 文件内容
     */
    public static String readLinesByJava7FilesInLines() {
        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME_INPUT), StandardCharsets.UTF_8)
        ) {
            StringBuilder content = new StringBuilder();
            lines.forEach(s -> content.append(s).append(SEPARATOR));
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
