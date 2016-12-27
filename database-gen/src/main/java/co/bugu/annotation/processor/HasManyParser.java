package co.bugu.annotation.processor;

import co.bugu.annotation.HasMany;
import co.bugu.entity.HasManyInfo;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.*;

/**
 * 处理一对多关联
 * Created by daocers on 2016/9/27.
 */
public class HasManyParser {
    public static HasManyInfo parseHasMany(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        HasManyInfo info = new HasManyInfo();

        Type type = field.getGenericType();
        if(type instanceof ParameterizedType){
            info.setFollowClass(((ParameterizedType)type).getActualTypeArguments()[0].getTypeName());
        }

        HasMany hasMany = field.getAnnotation(HasMany.class);
        if(hasMany == null){
            return null;
        }


        String key = getAnnotationValue(hasMany, "key");
        String column = getAnnotationValue(hasMany, "column");
        String table = getAnnotationValue(hasMany, "table");
        boolean sort = false;
        Method method = hasMany.annotationType().getDeclaredMethod("sort", null);
        if(method != null){
            sort = (boolean) method.invoke(hasMany, null);
        }

        boolean needConnTable = false;
        method = hasMany.annotationType().getDeclaredMethod("connTable", null);
        if(method != null){
            needConnTable = (boolean) method.invoke(hasMany, null);
        }
        info.setKey(key);
        info.setColumn(column);
        info.setTableName(table);
        info.setSort(sort);
        info.setNeedConnTable(needConnTable);
        return info;

    }

    private static String getAnnotationValue(HasMany hasMany, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = hasMany.annotationType().getDeclaredMethod(methodName, null);
        if(method != null){
            String val = (String) method.invoke(hasMany, null);
            if(!StringUtils.isEmpty(val)){
                return val;
            }
        }
        return null;
    }
}
