# java-convert-example

> 平时的java项目中会存在各种对象的互相转换的情况，本项目记录一些常见对象转换的方法，例如：文件转换、日期时间转换、stream流转换、集合对象转换等


## 文件

### String -> File

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

#### 使用 BufferedWriter

BufferedWriter 可以设置缓存区大小; OutputStreamWriter 可以指定字符集编码; FileOutputStream 可以指定文件编辑模式(追加/覆盖)。3

使用 PrintWriter/FileWriter 一般写入的内容比较少，如果需要写入大量数据，应该要使用缓冲流，提高效。

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

#### 使用 jdk7 的 Files

由于 nio 是 java7 新增的内容，使用本方法需要首先将程序的 jdk 升级到 jdk7+

1. 如果写入的是字符数据，则需要设置字符编码 Charset
2. 如果写入的是字节数据（byte[]），不需要设置字符编码
3. 如果想在已存在的文件后追加内容，可以增加 {@link java.nio.file.StandardOpenOption#APPEND} 参数

DEMO 参考： [String2FileWithJava7FilesExample](https://github.com/zzycreate/java-convert-example/blob/master/src/main/java/io/github/zzycreate/example/file/string2file/String2FileWithJava7FilesExample.java)

```
    List<String> lines = Arrays.asList("The second line", "The second line");
    Path path = Paths.get("filename.txt");
    try {
        Files.write(path, lines, StandardCharsets.UTF_8);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

## 日期时间

## steam流

## 文件
