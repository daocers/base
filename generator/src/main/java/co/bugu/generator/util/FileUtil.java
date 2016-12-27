package co.bugu.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 文件操作
 *
 * @author duming
 */
public class FileUtil {

    public static void del(String path) {
        File file = new File(path);
        System.out.println(file.delete());

    }

    public static void create(String path) {
        File file = new File(path);
        file.mkdirs();
    }

    public static String getAbsPath() {
        String rootPath = System.getProperty("user.dir");
        if (rootPath.indexOf("generator") > -1) {
            return rootPath;
        } else {
            return rootPath + "/generator/";
        }

    }

    public static InputStream getStream(String path) {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }
}
