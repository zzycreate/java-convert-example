package io.github.zzycreate.convert.example.file;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 向文件中写入内容
 * File to String
 *
 * @author zzycreate
 * @date 19-5-3
 * @see <a href="http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java">
 * http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java</a>
 */
public class StringToFileExample {

    /**
     * 使用 PrintWriter 向 File 中写内容
     * <p>
     * PrintWriter 是 jdk1.1 的 Writer 类, 需要手动关闭（Try-Catch-Finally）, 或者使用 jdk1.7 的 Try-With-Resources 写法
     */
    @Test
    public void writeFileWithPrintWriter() {

        // Try-Catch-Finally
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("the-file-name.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        // Try-With-Resources
        try (PrintWriter printWriter = new PrintWriter("the-file-name.txt", "UTF-8")) {
            printWriter.println("The first line");
            printWriter.println("The second line");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
