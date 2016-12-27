package co.bugu.annotation.processor;

import org.apache.commons.lang.StringUtils;

/**
 * 命名辅助类
 * 用于驼峰命名，下环线命名，和类名之间的互换
 * Created by daocers on 2016/9/27.
 */
public class NameUtil {
    /**
     * 类名转为驼峰命名法
     * @param className
     * @return
     */
    public static String toCamel(String className){
        if(StringUtils.isEmpty(className)){
            return "";
        }
        if(className.length() == 1){
            return className.toLowerCase();
        }
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    /**
     * 类名转换为下划线命名
     * @param className
     * @return
     */
    public static String toUnderLine(String className){
        if(StringUtils.isEmpty(className)){
            return "";
        }
        if(className.length() == 1){
            return className.toLowerCase();
        }
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        StringBuilder builder = new StringBuilder();
        for(char c: className.toCharArray()){
            if((c >= 'A' && c <= 'Z') || c == '$'){
                builder.append('_')
                        .append((c + "").toLowerCase());
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
