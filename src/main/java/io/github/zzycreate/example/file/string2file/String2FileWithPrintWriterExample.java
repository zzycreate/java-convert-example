package io.github.zzycreate.example.file.string2file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 向文件中写入内容
 * File to String
 * <p>
 * 使用 PrintWriter 向文件中写内容
 * <p>
 * PrintWriter 是 jdk1.1 的 Writer 类, 需要手动关闭（Try-Catch-Finally）, 见
 * {@link String2FileWithPrintWriterExample#useInTryCatchFinally()},
 * 或者使用 jdk1.7 的 Try-With-Resources 写法, 见
 * {@link String2FileWithPrintWriterExample#useInTryWithResources()}
 *
 * 缺点：
 * 1. 如果文件已经存在，PrintWriter会将文件大小截断为零，如果不想截断文件，可以使用FileWriter作为替代，FileWriter可以设置字符
 * 大小和缓冲大小
 * 2. 使用 PrintWriter 不需要输入文件 path，所以创建文件的路径需要看执行程序时的工作目录
 * 3. PriteWriter 会导致吞异常，见
 * <a href="https://stackoverflow.com/questions/1747040/difference-between-java-io-printwriter-and-java-io-bufferedwriter/1747092#1747092">stackoverflow.com/a/1747092/4678667</a>
 *
 * @author zzycreate
 * @date 19-5-3
 * @see <a href="http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java">
 * http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java</a>
 */
public class String2FileWithPrintWriterExample {

    public static final String FILE_NAME1 = "useInTryCatchFinally.txt";
    public static final String FILE_NAME2 = "useInTryWithResources.txt";
    public static final String LINE1 = "The first line";
    public static final String LINE2 = "The second line";
    public static final String SEPARATOR = System.getProperty("line.separator");

    public static void useInTryCatchFinally() {

        // Try-Catch-Finally
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_NAME1, "UTF-8");
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

    public static void useInTryWithResources() {

        // Try-With-Resources
        try (PrintWriter writer = new PrintWriter(FILE_NAME2, "UTF-8")) {
            writer.println(LINE1);
            writer.println(LINE2);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
