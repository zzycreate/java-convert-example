package io.github.zzycreate.example.file;

/**
 * @author zzycreate
 * @date 19-5-5
 */
public interface Constant {

    String LINE1 = "The first line";
    String LINE2 = "The second line";
    String SEPARATOR = System.getProperty("line.separator");
    String CONTENT = LINE1 + SEPARATOR + LINE2 + SEPARATOR;

    String FILE_NAME_INPUT = "input.txt";
}
