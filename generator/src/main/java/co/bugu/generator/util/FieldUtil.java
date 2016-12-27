package co.bugu.generator.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * field辅助类，用于获取标注之类的信息
 * Created by daocers on 2016/10/11.
 */
public class FieldUtil {

    /**
     * 获取字段默认名称
     * @param field
     * @return
     */
    public static String getDefaultDbName(Field field){
        String name = field.getName();
        char[] chars = name.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(char c: chars){
            if(('A' <= c  && c <= 'Z') || c == '$'){
                builder.append("_").append((c + "").toLowerCase());
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 获取字段类型
     * @param field
     * @return
     */
    public static String getType(Field field){
        Type type = field.getGenericType();
        if(type instanceof ParameterizedType){

        }else{

        }
        return null;
    }
}
