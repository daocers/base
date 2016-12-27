package com.daocers.generator.parser;

import co.bugu.annotation.Ignore;
import co.bugu.annotation.processor.NameUtil;
import co.bugu.framework.core.util.ReflectUtil;
import com.daocers.generator.Entity.TableInfo;
import com.daocers.generator.annotation.Id;
import com.daocers.generator.annotation.Table;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by daocers on 2016/9/29.
 */
public class BuguParser {

    public static List<TableInfo> parse(String packName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Set<Class<?>> classSet = ReflectUtil.getClasses(packName);
        for (Class clazz : classSet) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setClassName(clazz.getName());
            Ignore ignore = (Ignore) clazz.getAnnotation(Ignore.class);
            boolean isIgnore = parseIgnore(ignore);
            if (!isIgnore) {//不忽略进行处理
                Table table = (Table) clazz.getAnnotation(Table.class);
                String tableName = parseTable(table, clazz);
                tableInfo.setName(tableName);
                Field[] fields = clazz.getDeclaredFields();
                for(Field field: fields){
                    ignore = field.getAnnotation(Ignore.class);
                    if(ignore == null){//不忽略进行处理

                    }
                }
            }
        }
        return null;
    }


    /**
     * 解析table注解，
     *
     * @param table
     * @param clazz
     * @return 返回表名
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static String parseTable(Table table, Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String tableName = "";

        if (table != null) {
            Method method = table.annotationType().getDeclaredMethod("value", null);
            tableName = (String) method.invoke(table, null);
        }
        if (StringUtils.isEmpty(tableName)) {
            tableName = NameUtil.toCamel(clazz.getSimpleName());
        }
        return tableName;
    }

    private static void parseId() {

    }

    private static void parseIndex() {

    }

    private static boolean parseIgnore(Ignore ignore) {
        if (ignore != null) {
            return true;
        }
        return false;
    }

    private static void parseColumn() {

    }

    private static void parseCompIndex() {

    }

    private static void parseJoinTable() {

    }

    private static void parseReverse() {

    }
}
