package io.github.zzycreate.example.file.string2file;

import lombok.Cleanup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static io.github.zzycreate.example.constant.FileConstant.LINE1;
import static io.github.zzycreate.example.constant.FileConstant.LINE2;
import static io.github.zzycreate.example.constant.FileConstant.SEPARATOR;
import static io.github.zzycreate.example.constant.FileConstant.CONTENT;

/**
 * 使用 Writer 向文件中写入内容
 * File to String using Writer
 *
 * @author zzycreate
 * @date 19-5-3
 * @see <a href="https://stackoverflow.com/questions/2885173">stackoverflow.com/a/2885173</a>
 */
public class String2FileWithWriterExample {

    public static final String FILE_NAME_PRINT_WRITER1 = "writeByPrintWriterInTryCatchFinally.txt";
    public static final String FILE_NAME_PRINT_WRITER2 = "writeByPrintWriterInTryWithResources.txt";
    public static final String FILE_NAME_PRINT_WRITER3 = "writeByPrintWriterInLombokCleanUp.txt";
    public static final String FILE_NAME_FILE_WRITER = "writeByFileWriter.txt";
    public static final String FILE_NAME_BUFFERED_WRITER = "useBufferedWriter.txt";

    /**
     * jdk1.7以前使用 try-catch-finally，jdk1.7以后可以使用 try-with-resources
     */
    public static void writeByPrintWriterInTryCatchFinally() {

        // Try-Catch-Finally
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_NAME_PRINT_WRITER1, "UTF-8");
            writer.println(LINE1);
            writer.println(LINE2);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * 使用 PrintWriter 向文件中写内容
     * <p>
     * PrintWriter 是 jdk1.1 的 Writer 类, 需要手动关闭（Try-Catch-Finally）, 见
     * writeByPrintWriterInTryCatchFinally() 方法
     * 或者使用 jdk1.7 的 Try-With-Resources 写法, 见
     * writeByPrintWriterInTryWithResources() 方法
     * <p>
     * 缺点：
     * 1. 如果文件已经存在，PrintWriter会将文件大小截断为零，如果不想截断文件，可以使用FileWriter作为替代，FileWriter可以设置字符
     * 大小和缓冲大小
     * 2. 使用 PrintWriter 不需要输入文件 path，所以创建文件的路径需要看执行程序时的工作目录
     * 3. PriteWriter 会导致吞异常，见
     * <a href="https://stackoverflow.com/questions/1747040/difference-between-java-io-printwriter-and-java-io-bufferedwriter/1747092#1747092">stackoverflow.com/a/1747092/4678667</a>
     */
    public static void writeByPrintWriterInTryWithResources() {

        // Try-With-Resources
        try (PrintWriter writer = new PrintWriter(FILE_NAME_PRINT_WRITER2, "UTF-8")) {
            writer.println(LINE1);
            writer.println(LINE2);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用Lombok的 {@link Cleanup @CleanUp} 注解可以省略文件流的手动关闭过程，
     * 编译时Lombok插件会在整段使用了该流对象外面添加 try-catch-finally 代码块，从而不需要显示的书写关闭流的代码，
     * lombok @CLeanUp 详细用法参考： https://projectlombok.org/features/Cleanup
     */
    public static void writeByPrintWriterInLombokCleanUp() {
        try {
            @Cleanup PrintWriter writer = new PrintWriter(FILE_NAME_PRINT_WRITER3, "UTF-8");
            writer.println(LINE1);
            writer.println(LINE2);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileWriter 在创建的时候可以指定追加/覆盖模式，避免 PrintWriter 截断已存在的文件。
     */
    public static void writeByFileWriter() {

        try (FileWriter writer = new FileWriter(new File(FILE_NAME_FILE_WRITER), true)) {
            writer.write(LINE1 + SEPARATOR);
            writer.append(LINE2).append(SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * BufferedWriter 可以设置缓存区大小; OutputStreamWriter 可以指定字符集编码; FileOutputStream 可以指定文件编辑模式(追加/覆盖)
     * 使用 PrintWriter/FileWriter 一般写入的内容比较少，如果需要写入大量数据，应该要使用缓冲流，提高效率
     */
    public static void writeByBufferdWriter() {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_NAME_BUFFERED_WRITER, true), StandardCharsets.UTF_8))) {
            writer.write(CONTENT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
