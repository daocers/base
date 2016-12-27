package co.bugu.annotation.processor;

import co.bugu.annotation.Ignore;

import java.lang.reflect.Field;

/**
 * 是否忽略
 * Created by daocers on 2016/9/27.
 */
public class IgnoreParser {
    /**
     * 根据有没有这个注解判断是否需要忽略该字段
     * @param field
     * @return
     */
    public static boolean parseIgnore(Field field){

        Ignore ignore = field.getAnnotation(Ignore.class);
        if(ignore != null){
            return true;
        }
        return false;
    }
}
