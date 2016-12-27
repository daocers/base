package co.bugu.annotation.processor;

import co.bugu.annotation.Table;
import co.bugu.entity.TableInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注解处理器
 * Created by daocers on 2016/9/27.
 */
public class TableProcessor {
    private static Logger logger = LoggerFactory.getLogger(TableProcessor.class);

    /**
     * 处理表数据
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static TableInfo processClass(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TableInfo tableInfo = new TableInfo();
        String tableName = "";
        Annotation annotation = clazz.getDeclaredAnnotation(Table.class);
        Method valMethod = annotation.annotationType().getDeclaredMethod("value", null);
        if(valMethod != null){
            String value = (String) valMethod.invoke(annotation, null);
            if(StringUtils.isNotEmpty(value)){
                tableName = value.trim();
            }
        }
        if(StringUtils.isEmpty(tableName)){
            tableName = NameUtil.toUnderLine(clazz.getSimpleName());
        }
        tableInfo.setName(tableName);

        Method commentMethod = annotation.annotationType().getDeclaredMethod("comment", null);
        if(commentMethod != null){
            String comment = (String) commentMethod.invoke(annotation, null);
            tableInfo.setComment(comment);
        }
        if(tableInfo.getComment() == null){
            tableInfo.setComment("");
        }
        return tableInfo;
    }

}
