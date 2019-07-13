# java-convert-example

> 平时的java项目中会存在各种对象的互相转换的情况，本项目记录一些常见对象转换的方法，例如：文件转换、日期时间转换、stream流转换、集合对象转换等

## 文件

Java 为文件操作设计了很多的类，有数据相关的 IO Stream 流，与文件相关的File、Path等对文件系统的抽象。  
Java 的 io 包和几乎包含了所有操作输入、输出需要的类，另外 java1.4 开始推出的 nio 接口能更高效的完成一些工作。  
Java io 包中的流支持很多种格式，比如：基本类型、对象、本地化字符集等等。所有这些流类代表了输入源和输出目标。  
一个流可以理解为一个数据的序列。输入流表示从一个源读取数据，输出流表示向一个目标写数据。  

### IO

#### IO 流分类：

- 按照流的流向分，可以分为输入流和输出流；
- 按照操作单元划分，可以划分为字节流和字符流；
- 按照流的角色划分为节点流和处理流。

按照操作方式分类：

![java IO Stream 操作方式分类](./java-convert-example/java-io-classify1.jpg)

按照操作对象分类：

![java IO Stream 操作对象分类](./java-convert-example/java-io-classify2.jpg)

#### IO 流基础对象：

- InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。
- OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。

### NIO

java 1.4 版本后，重新设计出了一套新的 IO 接口：Non-blocking IO(NIO)

#### NIO 与 IO 的区别：

1. IO是面向流的，NIO是面向缓冲区的；
2. IO流是阻塞的，NIO流是不阻塞的;
3. NIO有选择器，而IO没有。

#### 读写数据方式：

- 从通道进行数据读取 ：创建一个缓冲区，然后请求通道读取数据。
- 从通道进行数据写入 ：创建一个缓冲区，填充数据，并要求通道写入数据。

### 文件

java 文件操作的基础是 File/Path, 一个代表文件，一个代表路径，两者组合完整地描述各种操作系统中的文件，尤其适合 Linux 的 
'Everything is a File' 的哲学。

### String -> File （Write File）

#### 使用 NIO 的 Files **(推荐)**

由于 Files 是 nio 在 java7 新增的内容，使用本方法需要首先将程序的 jdk 升级到 jdk7+; Files 工具类提供了各种读写创建删除文件等操作，可以很方便的操作文件和流。

1. 如果写入的是字符数据，则需要设置字符编码 Charset
2. 如果写入的是字节数据（byte[]），不需要设置字符编码
3. 如果想在已存在的文件后追加内容，可以增加 java.nio.file.StandardOpenOption#APPEND 参数

DEMO 参考： [String2FileWithJavaNioExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithJavaNioExample.java)

```
    List<String> lines = Arrays.asList("The second line", "The second line");
    Path path = Paths.get("filename.txt");
    try {
        Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 NIO 的 Channel

标准的IO编程接口是面向字节流和字符流的。而NIO是面向通道和缓冲区的，数据总是从通道中读到buffer缓冲区内，或者从buffer写入到通道中。

Java NIO 的 Channel 和 IO 流的对比：

1. 通道可以读也可以写，流一般来说是单向的（只能读或者写）。
2. 通道可以异步读写。
3. 通道总是基于缓冲区Buffer来读写。

DEMO 参考： [String2FileWithJavaNioExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithJavaNioExample.java)


使用 FileOutputStream 的 FileChannel：

```
    String data = "The second line" + System.getProperty("line.separator") + 
                          "The second line" + System.getProperty("line.separator");
    // use ByteBuffer wrap data
    final ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
    // try-with-resources auto close the channel
    try (// open channel
        final FileOutputStream fos = new FileOutputStream(new File("filename.txt"));
        FileChannel channel = fos.getChannel()) {
        // write buffer to channel
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
```

使用 RandomAccessFile 的 FileChannel：

```
    String data = "The second line" + System.getProperty("line.separator") + 
                          "The second line" + System.getProperty("line.separator");
    final ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
    try(final RandomAccessFile file = new RandomAccessFile("filename.txt", "rw");
        FileChannel channel = file.getChannel()){
        
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 BufferedWriter **(推荐)**

1. BufferedWriter 可以设置缓存区大小
2. OutputStreamWriter 可以指定字符集编码   
3. FileOutputStream 可以指定文件编辑模式(追加/覆盖)

使用 PrintWriter/FileWriter 一般写入的内容比较少，如果需要写入大量数据，应该要使用缓冲流，提高效率。

DEMO 参考： [String2FileWithWriterExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithWriterExample.java)

```
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("filename.txt", true), StandardCharsets.UTF_8))) {
        writer.write("The second line" + System.getProperty("line.separator") + 
        "The second line" + System.getProperty("line.separator"));
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 PrintWrite

使用PrintWrite可以很简单的使用println进行单行输入，但是存在的文件会被截断

1. 如果文件已经存在，PrintWriter 会将文件大小截断为零，如果不想截断文件，可以使用FileWriter作为替代，FileWriter设置字符大小和缓冲大小。
2. 使用 PrintWriter 不需要输入文件 path，所以创建文件的路径需要看执行程序时的工作目录。
3. PriteWriter 会导致吞异常，见 [stackoverflow.com/a/1747092/4678667](https://stackoverflow.com/questions/1747040/difference-between-java-io-printwriter-and-java-io-bufferedwriter/1747092#1747092) 。

DEMO 参考： [String2FileWithWriterExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithWriterExample.java)

```
    try (PrintWriter writer = new PrintWriter("filename.txt", "UTF-8")) {
        writer.println("The first line");
        writer.println("The second line");
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
        e.printStackTrace();
    }
```

#### 使用 FileWriter

FileWriter 在创建的时候可以指定追加/覆盖模式，避免 PrintWriter 截断已存在的文件。

DEMO 参考： [String2FileWithWriterExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithWriterExample.java)

```
    try (FileWriter writer = new FileWriter(new File("filename.txt"), true)) {
        writer.append("The first line").append(System.getProperty("line.separator"))
                .append(LINE2).append(System.getProperty("line.separator"));
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 commons-io 的 FileUtils

Commons-io 项目地址： [apache/commons-io](https://commons.apache.org/proper/commons-io/)  
使用 commons-io 包中的 FileUtils 进行文件写入, 重构的方法可以设置文件编码和写入模式

DEMO 参考： [String2FileWithCommonsExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithCommonsExample.java)

```
    try {
        String data = "The second line" + System.getProperty("line.separator") + 
                              "The second line" + System.getProperty("line.separator");
        FileUtils.writeStringToFile(new File("filename.txt"), data, StandardCharsets.UTF_8, true);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 guava 的 Files

Guava 的官方开源项目地址： [google/guava](https://github.com/google/guava)  
guava 的 Files 可以直接向文件中写入 byte[] 数据  
Files 已被标注为 @Beta 不稳定的版本，并提示使用 jdk7 nio 的 Files、MoreFiles 等工具类利用 nio 的 Path 进行文件处理  

DEMO 参考： [String2FileWithGuavaExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithGuavaExample.java)

```
    try {
        String data = "The second line" + System.getProperty("line.separator") + 
                          "The second line" + System.getProperty("line.separator");
        Files.write(data.getBytes(), new File("filename.txt"));
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 guava 的 CharSink/ByteSink

guava 的 CharSink 可以直接向文件中写入字符数据，ByteSink 则可以写入字节数据  
Files 中的 write、append、copy 等方法本质上都是调用的这些 Sink 类。    

DEMO 参考： [String2FileWithGuavaExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithGuavaExample.java)

```
    try {
        String data = "The second line" + System.getProperty("line.separator") + 
                          "The second line" + System.getProperty("line.separator");
       Files.asCharSink(new File("filename.txt"), StandardCharsets.UTF_8, FileWriteMode.APPEND).write(data);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

### File -> String (Read File)

#### 使用 NIO 的 Files **(推荐)**

NIO 的 Files 工具类提供多种 read 方法可以快速进行文件读取

DEMO 参考： [File2StringWithNioExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2string/File2StringWithNioExample.java)

使用 readAllBytes 读取字节：

```
    try {
        return new String(Files.readAllBytes(Paths.get("filename.txt")));
    } catch (IOException e) {
        e.printStackTrace();
    }
```

使用 readAllLines 逐行读取字符串：

```
    try {
        List<String> lines = Files.readAllLines(Paths.get("filename.txt"), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        lines.forEach(s -> sb.append(s).append(SEPARATOR));
        return sb.toString();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
```

由于 Stream 内部无法在 lambda 中使用带有 checked Exception 的方法，因此要对异常的抓取，防止发生异常  

使用 lines 逐行读取字符串：

```
    try (Stream<String> lines = Files.lines(Paths.get("filename.txt"), StandardCharsets.UTF_8)) {
        StringBuilder content = new StringBuilder();
        lines.forEach(s -> content.append(s).append(SEPARATOR));
        return content.toString();
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 Scanner 

Scanner 默认的缓冲大小为 1024, 可以读取文件，但是读取空文件的时候会出现异常，见 [Scanner的讨论](https://stackoverflow
.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file)  
Scanner 使用正则表达式 `\A.` 匹配开始节点。 

DEMO 参考： [File2StringWithScannerExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2string/File2StringWithScannerExample.java)


```
    try (Scanner scanner = new Scanner(new File("filename.txt"), "UTF-8")) {
        return scanner.useDelimiter("\\A").next();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
```

#### 使用 BufferedReader **(推荐)**

BufferedReader 缓冲流，可以高效的读取文件，使用 Reader 和 InputStream 组合可以设置各种配置。  
1. 使用 InputStreamReader 可以设置字符集
2. 使用 FileInputStream 可以读取文件
3. 或者使用 FileReader 直接读取文件，并用 BufferedReader 包装成缓存使用

DEMO 参考： [File2StringWithReaderExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2string/File2StringWithReaderExample.java)

```
    // new BufferedReader(new FileReader("filename.txt"));
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("filename.txt"), StandardCharsets.UTF_8))) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append(SEPARATOR);
        }
        return stringBuilder.toString();
    } catch (IOException e) {
        e.printStackTrace();
    }
```

使用 BufferReader 的 lines 方法逐行读取数据：

```
    try (BufferedReader reader = new BufferedReader(new FileReader("filename.txt"))) {
        return reader.lines().collect(Collectors.joining(System.getProperty("line.separator"));
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 FileInputStream

仅仅使用 FileInputStream 进行文件读取，从 FileInputStream 写入 byte[] 缓存，转换为文本数据，这段代码只需要 jdk1.0+

DEMO 参考： [File2StringWithReaderExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2string/File2StringWithReaderExample.java)

```
    FileInputStream fis = null;
    try {
        fis = new FileInputStream("filename.txt");
        byte[] buffer = new byte[fis.available()];
        int length = fis.read(buffer);
        return new String(buffer, 0, length, StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return null;
```

#### 使用 commons-io 的 IOUtils

commons-io 的 IOUtils 工具类提供了大量 IO 相关的工具方法，使用 IOUtils.copy 直接将 Reader/InputStream 转换为 Writer/OutputStream  

DEMO 参考： [File2StringWithCommonsExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2string/File2StringWithCommonsExample.java)

使用 FileReader 读取文件，使用 StringWriter 将流中的内容输出出来:

```
    try (FileReader fileReader = new FileReader("filename.txt");
         StringWriter stringWriter = new StringWriter()) {
        IOUtils.copy(fileReader, stringWriter);
        return stringWriter.toString();
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 commons-io 的 FileUtils

commons-io 的 FileUtils 提供了文件相关的工具方法， 使用 FileUtils.readByFileUtilsReadFileToString 读取文件内容

DEMO 参考： [File2StringWithCommonsExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2string/File2StringWithCommonsExample.java)

```
    try {
        return FileUtils.readFileToString(new File("filename.txt"), StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

#### 使用 guava 的 Files

直接使用 guava 的 Files 转换为 ByteSource/CharSource 然后直接 read 成字符串:

```
    try {
        // Resources.asCharSource(new URL(""), StandardCharsets.UTF_8).read();
        return Files.asCharSource(new File("filename.txt"), StandardCharsets.UTF_8).read();
    } catch (IOException e) {
        e.printStackTrace();
    }
```

或者使用 readLines 逐行读取：

```
    try {
        return String.join(System.getProperty("line.separator"), Files.readLines(new File("filename.txt"), StandardCharsets.UTF_8));
    } catch (IOException e) {
        e.printStackTrace();
    }
```

### File -> File (copy File)

#### 使用 NIO 的 Channel

标准的IO编程接口是面向字节流和字符流的。而NIO是面向通道和缓冲区的，数据总是从通道中读到buffer缓冲区内，或者从buffer写入到通道中。

Java NIO 的 Channel 和 IO 流的对比：

1. 通道可以读也可以写，流一般来说是单向的（只能读或者写）。
2. 通道可以异步读写。
3. 通道总是基于缓冲区Buffer来读写。

Buffer 及其子类不是线程安全的

Buffer 的属性：

1. capacity - 缓存容量
2. limit - 缓存下表限制值
3. position - 当前操作的下标值
4. mark - 临时下标值

几个属性的关系： 0 <= mark <= position <= limit <= capacity

Buffer 的方法：

1. mark(): mark 设置成 position
3. clear(): mark 设置为 -1 清除标记, position 设置为 0, limit 设置为 capacity, **数据写入 Buffer 前调用**, 或者说是 Channel 读取之前调用
4. flip(): limit 设置成 position 当前位置作为上限, position 设置为 0, **Buffer 读取数据前调用**，或者说是 Channel 写入之前调用
5. rewind(): position 设置为 0, limit 不变, **数据重新写入 Buffer 前调用**

使用不同的 Channel 同时操作同一个 Buffer 可以实现文件的复制。

DEMO 参考： [File2FileWithNioExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/file2file/File2FileWithNioExample.java)


```
    try (FileInputStream input = new FileInputStream("filename.txt");
         FileOutputStream output = new FileOutputStream("filename.txt");
         ReadableByteChannel from = input.getChannel();
         WritableByteChannel to = output.getChannel()) {
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
```

## 日期时间

### java 的时间操作 API

#### java.util 包的时间操作

`jdk 1.1` 就开始提供了基础的日期时间相关的功能，`java.util.Date`、`java.util.Calendar`、`java.text.DateFormat`、`java.sql.Date`、`java.sql.Time`、
`java.sql.Timestamp` 等是 `jdk 1.1` 中常用的日期时间类，`java.util.Date` 代表了时间，`java.util.Calendar` 代表了日期，`java.text.DateFormat` 
用于格式化时间，`java.sql.Date` 是 `java.util.Date` 的包装类，代表了数据库中的 DATE，`java.sql.Time` 代表数据库的时间（不含日期）, `java.sql.Timestamp` 代表了数据库的 TIMESTAMP。

SimpleDateFormat 时间格式化常用字符及含义：

| 字母	| 含义	| 示例    |
| ---   | ---   | ---   |
| G | 纪元 | 在 Locale.CHINA语言环境下，如 '公元';<br/>在Locale.US语言环境下，如 'AD' |
| y	| 年份。一般用 yy 表示两位年份，yyyy 表示 4 位年份	| 使用 yy 表示的年扮，如 11；<br/>使用 yyyy 表示的年份，如 2011 | 
| M	| 月份。一般用 MM 表示月份，如果使用 MMM，则会根据语言环境显示不同语言的月份	| 使用 MM 表示的月份，如 05；<br/>使用 MMM 表示月份，在 Locale.CHINA语言环境下，如“十月”；<br/>在Locale.US语言环境下，如 Oct | 
| d	| 月份中的天数。一般用 dd 表示天数	| 使用 dd 表示的天数，如 10 |
| D	| 年份中的天数。表示当天是当年的第几天， 用 D 表示	| 使用 D 表示的年份中的天数，如 295 |
| E	| 星期几。用 E 表示，会根据语言环境的不同， 显示不同语言的星期几	| 使用 E 表示星期几，<br/>在 Locale.CHINA 语言环境下，如“星期四”；<br/>在 Locale.US 语言环境下，如 Thu |
| H	| 一天中的小时数（0~23)。一般用 HH 表示小时数	| 使用 HH 表示的小时数，如 18 |
| h	| 一天中的小时数（1~12)。一般使用hh表 示小时数	| 使用 hh 表示的小时数，如 10 (注意 10 有可能是 10 点，也可能是 22 点）
| m	| 分钟数。一般使用 mm 表示分钟数	| 使用 mm 表示的分钟数，如 29 |
| s	| 秒数。一般使用 ss 表示秒数	| 使用 ss 表示的秒数，如 38 |
| S	| 毫秒数。一般使用 SSS 表示毫秒数	| 使用 SSS 表示的毫秒数，如 156 |

`Date` 代表了一个特定的时间，可以精确到毫秒，也能表示年月日等日期概念。

但 util 中的日期时间操作存在一些问题：

1. Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。
2. java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
3. 对于时间、时间戳、格式化以及解析，并没有一些明确定义的类。对于格式化和解析的需求，我们有java.text.DateFormat抽象类，但通常情况下，SimpleDateFormat类被用于此类需求。
4. 所有的日期类都是可变的，因此他们都不是线程安全的，这是Java日期类最大的问题之一。
5. 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。

#### java.time 包的时间操作

Java 8 新增的日期时间 API 遵循 JSR 310 设计规范，解决上述 API 中的缺陷。

主要的操作对象有：

1. java.time.LocalTime: 时间
2. java.time.LocalDate: 日期
3. java.time.LocalDateTime: 日期/时间
4. java.time.ZoneId: 时区
5. java.time.ZoneOffset: 时区偏移量
6. java.time.ZonedDateTime: 带时区信息的日期/时间
7. java.time.Instant: 时刻（时间戳）
8. java.time.Duration java.time.Period: 时间段

这些对象都是线程安全，且不可变，API 方法设计简单，转换容易，配合 `java.time.format.DateTimeFormatter` 可以很容易的进行格式化。

以上这些对象的关联关系：

1. LocalDateTime = LocalDate + LocalTime
2. ZonedDateTime = LocalDateTime + ZoneId/ZoneOffset
3. Instant = seconds + nano

Java 8日期/时间API是JSR-310的实现，它的实现目标是克服旧的日期时间实现中所有的缺陷，新的日期/时间API的一些设计原则是：

1. 不变性：新的日期/时间API中，所有的类都是不可变的，这对多线程环境有好处。
2. 关注点分离：新的API将人可读的日期时间和机器时间（unix timestamp）明确分离，它为日期（Date）、时间（Time）、日期时间（DateTime）、时间戳（unix timestamp）以及时区定义了不同的类。
3. 清晰：在所有的类中，方法都被明确定义用以完成相同的行为。举个例子，要拿到当前实例我们可以使用now()方法，在所有的类中都定义了format()和parse()
方法，而不是像以前那样专门有一个独立的类。为了更好的处理问题，所有的类都使用了工厂模式和策略模式，一旦你使用了其中某个类的方法，与其他类协同工作并不困难。
4. 实用操作：所有新的日期/时间API类都实现了一系列方法用以完成通用的任务，如：加、减、格式化、解析、从日期/时间中提取单独部分，等等。
5. 可扩展性：新的日期/时间API是工作在ISO-8601日历系统上的，但我们也可以将其应用在非IOS的日历上。


### java 1.1 日期/时间操作

#### 对象构造

示例代码： [Jdk1DateExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/Jdk1DateExample.java)


```
    // 毫秒值
    long timestamp = System.currentTimeMillis();

    // java.util.Date 除了一下两种构造方法，其他构造方法均已过期
    Date date = new Date();
    Date date1 = new Date(timestamp);

    // sql date/time/timestamp 是针对SQL语言使用的，Date只有日期而没有时间，Time只有时间而没有日期
    java.sql.Date sqlDate = new java.sql.Date(timestamp);
    java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
    java.sql.Time sqlTime = new java.sql.Time(timestamp);
    java.sql.Time sqlTime1 = new java.sql.Time(date1.getTime());
    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(timestamp);
    java.sql.Timestamp sqlTimestamp1 = new java.sql.Timestamp(date1.getTime());

    // Calendar 代表日历, GregorianCalendar 代表公历
    Calendar calendar = Calendar.getInstance();
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
```

#### 对象取值

示例代码： [Jdk1DateExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/Jdk1DateExample.java)

```
    long timestamp = System.currentTimeMillis();
    Date date = new Date(timestamp);
    java.sql.Date sqlDate = new java.sql.Date(timestamp);
    java.sql.Time sqlTime = new java.sql.Time(timestamp);
    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(timestamp);
    Calendar calendar = Calendar.getInstance();
    GregorianCalendar gregorianCalendar = new GregorianCalendar();

    // Date 除了 getTime() 方法可以获取时间戳毫秒值外，其他 get 方法在 jdk8 中均已过期，不推荐时间
    // sql date/time/timestamp 均为 util date 的子类，过期方法类似
    long s = date.getTime();
    long sqlTimes = sqlDate.getTime();
    long sqlTime1 = sqlTime.getTime();
    long sqlTime2 = sqlTimestamp.getTime();
    long sqlNano1 = sqlTimestamp.getNanos();
    long sqlNano2 = sqlTimestamp.getNanos();

    Date calendarTime = calendar.getTime();
    int year = calendar.get(Calendar.YEAR);
    // 由于月份是从0开始，一般 +1 之后才和人的主观感受一直
    int month = calendar.get(Calendar.MONTH) + 1;
    int dateNum = calendar.get(Calendar.DATE);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);
    // 星期
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    // 日期
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    boolean isLeapYear = gregorianCalendar.isLeapYear(year);
```

### java 8 日期/时间操作

JSR 303 的日期时间对象虽然多，但是操作的 API 基本类似，很容易理解，以下距离没有列举所有情况，但其他操作基本类似。

#### 对象构造

对象的创建基本都是 now (当前时间)、of (静态构造方法)、parse (文本解析)、at (维度组合)、to (对象转换)、between (区间) 等简单明了的 API。

示例代码： [Jdk8TimeExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/Jdk8TimeExample.java)

```
    LocalTime time = LocalTime.now();
    LocalTime time1 = LocalTime.of(13, 14, 15);
    LocalTime time2 = LocalTime.ofSecondOfDay(47655);
    LocalTime time3 = LocalTime.parse("13:14:15");

    LocalDate date = LocalDate.now();
    LocalDate date1 = LocalDate.of(2019, 5, 10);
    LocalDate date2 = LocalDate.ofYearDay(2019, 130);
    LocalDate date3 = LocalDate.parse("2019-05-10");

    LocalDateTime dateTime = LocalDateTime.now();
    LocalDateTime dateTime1 = LocalDateTime.of(2019, 5, 10, 13, 14, 15);
    LocalDateTime dateTime2 = LocalDateTime.of(date, time);
    LocalDateTime dateTime3 = date.atStartOfDay();
    LocalDateTime dateTime4 = date.atTime(time);
    LocalDateTime dateTime5 = date.atTime(13, 14, 15);
    LocalDateTime dateTime6 = time.atDate(date);

    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    ZonedDateTime zonedDateTime1 = dateTime.atZone(ZoneId.systemDefault());
    ZonedDateTime zonedDateTime2 = dateTime.atZone(ZoneId.of("Asia/Shanghai"));

    LocalDateTime dateTime7 = zonedDateTime.toLocalDateTime();
    LocalDate date4 = zonedDateTime.toLocalDate();
    LocalTime time4 = zonedDateTime.toLocalTime();

    Instant instant = dateTime.toInstant(ZoneOffset.of("+08:00"));
    Instant instant1 = zonedDateTime.toInstant();
    Instant instant2 = new Date().toInstant();

    Duration duration = Duration.between(time, time1);
    Period period = Period.between(date, date1);

```

#### 对象取值和比较

对象的 get 方法可以获取对象结构的组成部分，is方法可以用做时间的简单判断。

示例代码： [Jdk8TimeExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/Jdk8TimeExample.java)

```
    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    LocalDateTime dateTime = zonedDateTime.toLocalDateTime();
    LocalDate date = dateTime.toLocalDate();
    LocalTime time = dateTime.toLocalTime();

    int year = date.getYear();
    Month month = date.getMonth();
    int monthValue = date.getMonthValue();
    int dayOfYear = date.getDayOfYear();
    int dayOfMonth = date.getDayOfMonth();
    DayOfWeek dayOfWeek = date.getDayOfWeek();

    int hour = time.getHour();
    int minute = time.getMinute();
    int second = time.getSecond();
    int nano = time.getNano();

    ZoneId zone = zonedDateTime.getZone();
    ZoneOffset offset = zonedDateTime.getOffset();

    LocalDateTime now = LocalDateTime.now();

    System.out.println(String.format("dateTime is before now: %s", dateTime.isBefore(now)));
    System.out.println(String.format("dateTime is before now: %s", dateTime.isAfter(now)));
    System.out.println(String.format("date is leap year(闰年): %s", dateTime.toLocalDate().isLeapYear()));
```

### 类型转换

#### Date -> Instant

示例代码： [DateLocalDateTimeConvertExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateLocalDateTimeConvertExample.java)

```
    Instant instant = new Date().toInstant();
    Instant instant1 = Instant.ofEpochMilli(new Date().getTime())
```

#### Date -> LocalDateTime/LocalDate/LocalTime

示例代码： [DateLocalDateTimeConvertExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateLocalDateTimeConvertExample.java)

```
    LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
    LocalDate localDate = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();
    LocalTime localTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalTime();
```

#### Instant -> Date

示例代码： [DateLocalDateTimeConvertExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateLocalDateTimeConvertExample.java)

```
    Date date = new Date(Instant.now().toEpochMilli());
```

#### LocalDateTime -> Date

示例代码： [DateLocalDateTimeConvertExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateLocalDateTimeConvertExample.java)

```
    Date date = new Date(LocalDateTime.now().toInstant(ZoneOffset.of("+08:00")).toEpochMilli());
```

#### String -> Date

使用 `SimpleDateFormat` 按照格式解析字符串，可能会出现异常，格式见前文表格。

示例代码： [DateTimeStringExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateTimeStringExample.java)

```
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2019-01-02 15:23:46");
    } catch (ParseException e) {
        e.printStackTrace();
    }
```

#### String -> Date

使用 `SimpleDateFormat` 按照格式解析字符串，可能会出现异常，格式见前文表格。

示例代码： [DateTimeStringExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateTimeStringExample.java)

```
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2019-01-02 15:23:46");
    } catch (ParseException e) {
        e.printStackTrace();
    }
    
```

或者间接利用 LocalDateTime 进行字符串解析

```
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.parse("2019-05-10 13:14:15", dateTimeFormatter);
    Date date = Date.from(localDateTime.toInstant(ZoneOffset.of("+08:00")));
```

#### String -> LocalDateTime

示例代码： [DateTimeStringExample.java](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/datetime/DateTimeStringExample.java)

```
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime = DateTimeStringExample.toLocalDateTime("2019-05-10 13:14:15", dateTimeFormatter);
```