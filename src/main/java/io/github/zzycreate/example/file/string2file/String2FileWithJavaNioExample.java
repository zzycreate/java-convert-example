package io.github.zzycreate.example.file.string2file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static io.github.zzycreate.example.file.Constant.LINE1;
import static io.github.zzycreate.example.file.Constant.LINE2;
import static io.github.zzycreate.example.file.Constant.CONTENT;

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
 * @see <a href="https://stackoverflow.com/questions/2885173">stackoverflow.com/a/2885173</a>
 */
public class String2FileWithJavaNioExample {

    public static final String FILE_NAME_BY_FILES = "writeByJava7Files.txt";
    public static final String FILE_NAME_BY_CHANNEL1 = "writeByNioFileChannelViaFileOutputStream.txt";
    public static final String FILE_NAME_BY_CHANNEL2 = "writeByNioFileChannelViaRandomAccessFile.txt";

    /**
     * Files 工具类提供了各种读写创建删除文件等操作
     */
    public static void writeByJava7Files() {
        List<String> lines = Arrays.asList(LINE1, LINE2);
        Path path = Paths.get(FILE_NAME_BY_FILES);
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 标准的IO编程接口是面向字节流和字符流的。而NIO是面向通道和缓冲区的，数据总是从通道中读到buffer缓冲区内，或者从buffer写入到通道中。
     * Java NIO 的 Channel 和 IO 流的对比：
     * 1. 通道可以读也可以写，流一般来说是单向的（只能读或者写）。
     * 2. 通道可以异步读写。
     * 3. 通道总是基于缓冲区 Buffer 来读写。
     */
    public static void writeByNioFileChannelViaFileOutputStream() {

        // use ByteBuffer wrap data
        final ByteBuffer buffer = ByteBuffer.wrap(CONTENT.getBytes());
        // try-with-resources auto close the channel
        try (// open channel
             final FileOutputStream fos = new FileOutputStream(new File(FILE_NAME_BY_CHANNEL1));
             FileChannel channel = fos.getChannel()) {
            // write buffer to channel
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用 RandomAccessFile 创建 channel
     */
    public static void writeByNioFileChannelViaRandomAccessFile() {

        final ByteBuffer buffer = ByteBuffer.wrap(CONTENT.getBytes());
        try (final RandomAccessFile file = new RandomAccessFile(FILE_NAME_BY_CHANNEL2, "rw");
             FileChannel channel = file.getChannel()) {
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
