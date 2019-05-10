package io.github.zzycreate.example.constant;

/**
 * 文件操作常量
 *
 * @author zzycreate
 * @date 19-5-5
 */
public interface FileConstant {

    String LINE1 = "The first line";
    String LINE2 = "The second line";
    String SEPARATOR = System.getProperty("line.separator");
    String CONTENT = LINE1 + SEPARATOR + LINE2 + SEPARATOR;

    String FILE_NAME_INPUT = "input.txt";
}
