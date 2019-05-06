package io.github.zzycreate.example.file.file2file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import static io.github.zzycreate.example.file.Constant.FILE_NAME_INPUT;

/**
 * 标准的IO编程接口是面向字节流和字符流的。而NIO是面向通道和缓冲区的，数据总是从通道中读到buffer缓冲区内，或者从buffer写入到通道中。
 * <p>
 * Java NIO 的 Channel 和 IO 流的对比：
 * <p>
 * 1. 通道可以读也可以写，流一般来说是单向的（只能读或者写）。
 * 2. 通道可以异步读写。
 * 3. 通道总是基于缓冲区Buffer来读写。
 * <p>
 * Buffer 及其子类不是县城安全的
 * <p>
 * Buffer 的属性：
 * 1. capacity - 缓存容量
 * 2. limit - 缓存下表限制值
 * 3. position - 当前操作的下标值
 * 4. mark - 临时下标值
 * 几个属性的关系： 0 <= mark <= position <= limit <= capacity
 * <p>
 * Buffer 的方法：
 * 1. mark(): mark 设置成 position
 * 3. clear(): mark 设置为 -1 清除标记, position 设置为 0, limit 设置为 capacity, **数据写入 Buffer 前调用**
 * 4. flip(): limit 设置成 position 当前位置作为上限, position 设置为 0, **Buffer 读取数据前调用**
 * 5. rewind(): position 设置为 0, limit 不变, **数据重新写入 Buffer 前调用**
 *
 * @author zzycreate
 * @date 19-5-6
 */
public class File2FileWithNioExample {


    public static final String FILE_NAME_OUTPUT = "output.txt";

    /**
     * 使用 Channel 进行文件复制
     */
    public static void copeWithChannel() {

        try (
                FileInputStream input = new FileInputStream(FILE_NAME_INPUT);
                FileOutputStream output = new FileOutputStream(FILE_NAME_OUTPUT);
                ReadableByteChannel from = input.getChannel();
                WritableByteChannel to = output.getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
            while (from.read(buffer) != -1) {
                // Prepare the buffer to be drained
                buffer.flip();

                // Make sure that the buffer was fully drained
                while (buffer.hasRemaining()) {
                    to.write(buffer);
                }

                // Make the buffer empty, ready for filling
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
