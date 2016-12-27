package co.bugu.annotation.processor;

import co.bugu.annotation.Index;
import co.bugu.entity.FieldInfo;
import co.bugu.entity.IndexInfo;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理索引信息
 * Created by daocers on 2016/9/27.
 */
public class IndexParser {
    /**
     * 索引信息
     * 默认使用单列索引，
     * @param field
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static IndexInfo parseIndex(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IndexInfo info = new IndexInfo();
        Index index = field.getAnnotation(Index.class);
        if(index == null){
            return null;
        }
        Method method = index.annotationType().getDeclaredMethod("value", null);
        if(method != null){
            String value = (String) method.invoke(index, null);
            if(!StringUtils.isEmpty(value)){
                info.setName(value);
            }
        }
        if(StringUtils.isEmpty(info.getName())){
            info.setName(NameUtil.toUnderLine(field.getName()));
        }

        method = index.annotationType().getDeclaredMethod("columns", null);
        if(method != null){
            String[] cols = (String[]) method.invoke(index, null);
            List<String> list = new ArrayList<>();
            for(String col: cols){
                list.add(col);
            }
            info.setColumnList(list);
        }
        if(info.getColumnList() == null || info.getColumnList().size() == 0){
            List<String> list = new ArrayList<>();
            list.add(info.getName());
            info.setColumnList(list);
        }
        return info;

    }
}
