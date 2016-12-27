package co.bugu.annotation.processor;

import co.bugu.annotation.Id;
import co.bugu.entity.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * id注解处理器
 * Created by daocers on 2016/9/27.
 */
public class IdParser {

    /**
     * 处理id注解，如果是主键，同时设置非空，和主键以及是否自增属性
     * @param field
     * @param info
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static FieldInfo parseId(Field field, FieldInfo info) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Id id = field.getAnnotation(Id.class);
        if(id != null){
            info.setPrimaryKey(true);
            info.setNullable(false);
            Method autoIncrement = id.annotationType().getDeclaredMethod("autoIncrement", null);
            if(autoIncrement != null){
                boolean isIncrement = (boolean) autoIncrement.invoke(id, null);
                info.setAutoIncrement(isIncrement);
            }else{
                info.setAutoIncrement(true);
            }
        }
        return info;
    }
}
