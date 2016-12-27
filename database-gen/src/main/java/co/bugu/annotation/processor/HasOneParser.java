package co.bugu.annotation.processor;

import co.bugu.annotation.HasOne;
import co.bugu.entity.HasOneInfo;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 处理hasOne注解
 * Created by daocers on 2016/9/27.
 */
public class HasOneParser {
    public static HasOneInfo parseHasOne(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HasOneInfo hasOneInfo = new HasOneInfo();
        HasOne hasOne = field.getAnnotation(HasOne.class);
        if(hasOne == null){
            return null;
        }

        String value = getAnnotationValue(hasOne, "value");
        hasOneInfo.setClassName(field.getType().getName());
        hasOneInfo.setColName(value);

        Method method = hasOne.annotationType().getDeclaredMethod("reverse", null);
        if(method != null){
            boolean flag = (boolean) method.invoke(hasOne, null);
            hasOneInfo.setReverse(flag);
        }
        return hasOneInfo;

    }

    private static String getAnnotationValue(HasOne hasOne, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = hasOne.annotationType().getDeclaredMethod(methodName, null);
        if(method != null){
            String val = (String) method.invoke(hasOne, null);
            if(!StringUtils.isEmpty(val)){
                return val;
            }
        }
        return null;
    }
}
