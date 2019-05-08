package io.github.zzycreate.example.file.file2string;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static io.github.zzycreate.example.file.Constant.FILE_NAME_INPUT;

/**
 * @author zzycreate
 * @date 19-5-8
 */
public class File2StringWithScannerExample {

    /**
     * 使用 Scanner 读取文件内容
     *
     * @return 文件内容
     */
    public static String readByScanner() {
        try (Scanner scanner = new Scanner(new File(FILE_NAME_INPUT), "UTF-8")) {
            return scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
