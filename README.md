# java-convert-example

> 平时的java项目中会存在各种对象的互相转换的情况，本项目记录一些常见对象转换的方法，例如：文件转换、日期时间转换、stream流转换、集合对象转换等

## 1. 文件

### 1.1 String -> File

- #### 使用 PrintWrite

    - 如果文件已经存在，PrintWriter 会将文件大小截断为零，如果不想截断文件，可以使用FileWriter作为替代，FileWriter可以设置字符大小和缓冲大小。
    - 使用 PrintWriter 不需要输入文件 path，所以创建文件的路径需要看执行程序时的工作目录。
    - PriteWriter 会导致吞异常，见 [stackoverflow.com/a/1747092/4678667](https://stackoverflow
    .com/questions/1747040/difference-between-java-io-printwriter-and-java-io-bufferedwriter/1747092#1747092) 。

```java_holder_method_tree
    try (PrintWriter writer = new PrintWriter("filename.txt", "UTF-8")) {
        writer.println("The first line");
        writer.println("The second line");
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
        e.printStackTrace();
    }
``` 

- #### 使用 FileWriter

    FileWriter 在创建的时候可以指定追加/覆盖模式，避免 PrintWriter 截断已存在的文件。

```java_holder_method_tree
    try (FileWriter writer = new FileWriter(new File("filename.txt"), true)) {
        writer.append(LINE1).append(SEPARATOR)
                .append(LINE2).append(SEPARATOR);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

## 2. 日期时间

## 3. steam流

## 4. 文件
