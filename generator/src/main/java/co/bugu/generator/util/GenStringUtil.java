package co.bugu.generator.util;

/**
 * 字符串转换
 *
 * @author duming
 */
public class GenStringUtil {
    /**
     * abc_def ->AbcDef
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        String propertie = toLowerCase(str);
        return propertie.substring(0, 1).toUpperCase() + propertie.substring(1);
    }

    /**
     * AbcDef ->abcDef
     *
     * @param str
     * @return
     */
    public static String toVariableName(String str) {
        String propertie = toLowerCase(str);
        return propertie.substring(0, 1).toLowerCase() + propertie.substring(1);
    }

    /**
     * abc_def ->abcDef
     *
     * @param columnName
     * @return
     */
    public static String toLowerCase(String columnName) {
        String attribute = columnName.toLowerCase().trim();
        int index = -1;
        while ((index = attribute.indexOf("_")) != -1) {
            String str = attribute.substring(index + 1).trim();
            if ("".equals(str.trim())) {
                attribute = attribute.substring(0, index).trim();
                break;
            }
            attribute = attribute.substring(0, index).trim() + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return attribute;
    }

    public static String toPkgPath(String packageName) {
        return packageName.replace(".", "/");
    }

    public static String toJavaPath(String packageName) {
        return packageName.replace("/", "\\");
    }

}
