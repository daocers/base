package co.bugu.annotation.processor;

import co.bugu.annotation.Column;
import co.bugu.entity.FieldInfo;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * column注解处理器
 * Created by daocers on 2016/9/27.
 */
public class ColumnParser {
    public static FieldInfo parseColumn(Field field, FieldInfo info) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Column column = field.getAnnotation(Column.class);

        String defaultName = NameUtil.toUnderLine(field.getName());
        String defaultType = getDefaultByField(field);
        if(column != null){
            String name = getAnnotationValue(column, "value");
            String type = getAnnotationValue(column, "type");
            String length = getAnnotationValue(column, "length");
            String comment = getAnnotationValue(column, "comment");
            info.setName(name);
            info.setType(type);
            info.setLength(Integer.valueOf(length == null? "0" : length));
            info.setComment(comment);
        }
        if(StringUtils.isEmpty(info.getName())){
            info.setName(defaultName);
        }
        if(StringUtils.isEmpty(info.getType())){
            info.setType(defaultType);
        }
        if(info.getLength() == null || info.getLength() == 0){
            info.setLength(getDefaultLengthByType(info.getType()));
        }
        return info;
    }

    private static String getDefaultByField(Field field) {
        Class type = field.getType();
        String typeClass = field.getType().getName();
        if(Integer.class.getName().equals(typeClass)){
            return "int";
        }else if(double.class.getName().equals(typeClass)){
            return "double";
        }else if(float.class.getName().equals(typeClass)){
            return "float";
        }else if(String.class.getName().equals(typeClass)){
            return "varchar";
        }else if(Date.class.getName().equals(typeClass)){
            return "timestamp";
        }else if(BigDecimal.class.getName().equals(typeClass)){
            return "double";
        }else{
            return "varchar";
        }
    }

    private static int getDefaultLengthByType(String type){
        if("int".equals(type)){
            return 11;
        }else if("double".equals(type)){
            return 10;
        }else if("varchar".equals(type)){
            return 255;
        }else if("text".equals(type)){
            return 4000;
        }else{
            return 0;
        }
    }

    private static String getAnnotationValue(Column column, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = column.annotationType().getDeclaredMethod(methodName, null);
        if(method != null){
            String val = (String) method.invoke(column, null);
            if(!StringUtils.isEmpty(val)){
                return val;
            }
        }
        return null;
    }
}
