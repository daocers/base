package com.daocers.generator.parser;

import co.bugu.annotation.processor.NameUtil;
import co.bugu.framework.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.daocers.generator.Entity.ColumnInfo;
import com.daocers.generator.Entity.TableInfo;
import com.daocers.generator.annotation.*;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by daocers on 2016/9/29.
 */
public class Parser {
    public static boolean id(Field field) {
        Id id = field.getAnnotation(Id.class);
        if (id == null) {
            return false;
        } else {
            return true;
        }
    }


    private static TableInfo processCompIndex(TableInfo info, ColumnInfo columnInfo, Field field, Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CompIndex compIndex = field.getAnnotation(CompIndex.class);
        if (compIndex != null) {
            Method method = compIndex.annotationType().getDeclaredMethod("order", null);
            int order = (int) method.invoke(compIndex, null);
            if (order == 0) {//默认排序
                info.getIndexList().add(columnInfo.getName());
            }
        }
        return info;
    }

    /**
     * 处理index注解，索引信息
     *
     * @param info
     * @param field
     * @param clazz
     * @return
     */
    private static ColumnInfo processIndex(ColumnInfo info, Field field, Class clazz) {
        if (info.isIndex()) {
            return info;
        }
        Index index = field.getAnnotation(Index.class);
        if (index != null) {
            info.setIndex(true);
        }
        return info;
    }

    /**
     * 处理id注解
     * 只要是id，默认为索引列
     *
     * @param info
     * @param field
     * @param clazz
     * @return
     */
    private static ColumnInfo processId(ColumnInfo info, Field field, Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Id id = field.getAnnotation(Id.class);
        if (id != null) {
            info.setId(true);
            Method method = id.annotationType().getDeclaredMethod("autoIncrement", null);
            boolean autoIncrement = (boolean) method.invoke(id, null);
            info.setAutoCreament(autoIncrement);
            info.setIndex(true);
        } else {
            info.setId(false);
            info.setAutoCreament(false);
        }
        return info;
    }

    /**
     * 处理column注解，该注解只对基本类型有效
     * 有设置，按照设置，没有设置，使用默认
     *
     * @param field
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static ColumnInfo processColumn(Field field, Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Column column = field.getAnnotation(Column.class);

        ColumnInfo info = new ColumnInfo();
        if (column == null) {
            info = getDefaultByField(field, clazz);
        } else {
            String tmpName = "", tmpType = "";
            int tmpLength = 0;
            Method name = column.annotationType().getDeclaredMethod("name", null);
            Method type = column.annotationType().getDeclaredMethod("type", null);
            Method length = column.annotationType().getDeclaredMethod("length", null);
            if (name != null) {
                tmpName = (String) name.invoke(column, null);
            }
            if (type != null) {
                tmpType = (String) type.invoke(column, null);
            }
            if (length != null) {
                tmpLength = (int) length.invoke(column, null);
            }
            if (StringUtils.isEmpty(tmpName)) {
                info.setName(NameUtil.toUnderLine(field.getName()));
            }
            if (StringUtils.isEmpty(tmpType)) {
                info.setType(getDefaultTypeByField(field.getType().getName()));
            }
            if (tmpLength == 0) {
                info.setLength(getDefaultLengthByType(info.getType()));
            }
            return info;
        }
        return info;
    }

    /**
     * 解析table注解，
     *
     * @param clazz
     * @return 返回表名
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static String parseTable(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Table table = (Table) clazz.getAnnotation(Table.class);
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


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        getTableInfoList("com.daocers.domain");
        genSQL("com.daocers.domain");
    }

    public static void genSQL(String packName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<TableInfo> tableInfoList = getTableInfoList(packName);
        Map<String, TableInfo> map = new HashMap<>();
        for(TableInfo table: tableInfoList){
            if(!StringUtils.isEmpty(table.getName())){
                map.put(table.getClassName(), table);
            }
        }

        Iterator<Map.Entry<String, TableInfo>> iter = map.entrySet().iterator();
        for(TableInfo table : tableInfoList){
            if(StringUtils.isEmpty(table.getName())){
                for(ColumnInfo col: table.getColumnInfoList()){
                    String className = col.getClassName();
                    map.get(className).getColumnInfoList().add(col);
                }
            }
        }

        StringBuffer tb = new StringBuffer();
        StringBuffer ib = new StringBuffer();
        for(TableInfo table: tableInfoList){
            if(StringUtils.isEmpty(table.getName())){
                continue;
            }
            tb.append("CREATE TABLE ")
                    .append(table.getName())
                    .append("(\n");
            String pk = "";

            for(ColumnInfo col: table.getColumnInfoList()){
                tb.append("\t");
                if(col.isId()){//id
                    tb.append(col.getName())
                            .append(" ")
                            .append(col.getType())
                            .append("(" + col.getLength() + ") NOT NULL ")
                            .append(col.isAutoCreament() ? "AUTO_INCREMENT" : "")
                            .append(",\n");
                    pk = col.getName();
                    ib.append("CREATE UNIQUE INDEX ")
                            .append("idx_" + col.getName())
                            .append(" ON ")
                            .append(table.getName())
                            .append("(")
                            .append(col.getName())
                            .append(");\n");
                }else{
                    tb.append(col.getName())
                            .append(" ")
                            .append(col.getType());
                    if(col.getLength() > 0){
                        tb.append("(" + col.getLength() + ")");
                    }
                    tb.append(",\n");

                    if(col.isIndex()){
                        ib.append("CREATE INDEX ")
                                .append("idx_" + col.getName())
                                .append(" ON ")
                                .append(table.getName())
                                .append("(")
                                .append(col.getName())
                                .append(");\n");
                    }
                }
            }
            if(StringUtils.isEmpty(pk)){
                tb = new StringBuffer().append(tb.substring(0, tb.length() - 2));
            }else{
                tb.append("\tPRIMARY KEY (")
                        .append(pk)
                        .append(")\n");
            }
            tb.append(");\n");
        }
        System.out.println(tb.toString());
        System.out.println(ib.toString());
        System.out.println(JSON.toJSONString(map, true));

    }

    public static List<TableInfo> getTableInfoList(String packName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<TableInfo> tableInfoList = new ArrayList<>();
        Set<Class<?>> classSet = ReflectUtil.getClasses(packName);
        TableInfo tableInfo = null;
        List<Map<String, String>> one2manyMap = new ArrayList<>();

        for (Class clazz : classSet) {
            tableInfo = new TableInfo();
            String tableName = parseTable(clazz);
            tableInfo.setClassName(clazz.getName());
            tableInfo.setName(tableName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Ignore ignore = field.getAnnotation(Ignore.class);
                if (ignore != null) {
                    continue;
                }
                if (isGernericType(field)) {//泛型，忽略除了joinTable之外所有注解
                    TableInfo table = processRelativeTable(field, clazz);//一对多关联
                    tableInfoList.add(table);
                } else {//不是泛型
                    //不是基本类型
                    if (!isBasicType(field)) {//非基本类型，处理关联，
                        JoinTable joinTable = field.getAnnotation(JoinTable.class);
                        if (joinTable != null) {
                            TableInfo table = processRelativeTable(field, clazz);//一对一关联
                            tableInfoList.add(table);
                        } else {//一对一关联，不需要关联表
                            ColumnInfo columnInfo = new ColumnInfo();
                            columnInfo.setClassName(clazz.getName());
                            String fieldType = field.getType().getSimpleName();
                            fieldType = NameUtil.toUnderLine(fieldType);
                            columnInfo.setName(fieldType + "_id");
                            columnInfo.setType("INT");
                            columnInfo.setLength(11);
                            columnInfo.setForeignKey(true);
                            columnInfo.setIndex(true);
                            tableInfo.getColumnInfoList().add(columnInfo);
                        }
                    } else {//基本类型
                        //处理column
                        ColumnInfo info = processColumn(field, clazz);
                        //处理id
                        info = processId(info, field, clazz);
                        //处理index
                        info = processIndex(info, field, clazz);
                        //处理compIndex
                        tableInfo = processCompIndex(tableInfo, info, field, clazz);
                        tableInfo.getColumnInfoList().add(info);
                    }

                }
            }
            tableInfoList.add(tableInfo);
        }

        return tableInfoList;
//        System.out.println(JSON.toJSONString(tableInfoList, true));
    }


    /**
     * 处理关联关系
     * 包括一对一的关联表，一对多的关联表
     * 都需要生成额外的连接表
     *
     * @param field
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static TableInfo processRelativeTable(Field field, Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TableInfo tableInfo = new TableInfo();
        JoinTable joinTable = field.getAnnotation(JoinTable.class);
        String tmpName = "", tmpKey = "", tmpCol = "";
        boolean tmpSort = false;
        if (joinTable != null) {//需要关联表
            Method name = joinTable.annotationType().getDeclaredMethod("name", null);
            if (name != null) {
                tmpName = (String) name.invoke(joinTable, null);
            }
            Method key = joinTable.annotationType().getDeclaredMethod("key", null);
            if (key != null) {
                tmpKey = (String) key.invoke(joinTable, null);
            }
            Method column = joinTable.annotationType().getDeclaredMethod("column", null);
            if (column != null) {
                tmpCol = (String) column.invoke(joinTable, null);
            }
            Method sort = joinTable.annotationType().getDeclaredMethod("sort", null);
            if (sort != null) {
                tmpSort = (boolean) sort.invoke(joinTable, null);
            }

            String relateTable = NameUtil.toUnderLine(getRelativeSimpleClass(field));
            if (StringUtils.isEmpty(tmpName)) {
                tmpName = NameUtil.toUnderLine(clazz.getSimpleName());
            }
            if (StringUtils.isEmpty(tmpKey)) {
                tmpKey = NameUtil.toUnderLine(clazz.getSimpleName()) + "_id";
            }
            if (StringUtils.isEmpty(tmpCol)) {
                tmpCol = relateTable + "_id";
            }

            return getRelativeTable(tmpName, tmpKey, tmpCol, tmpSort);
        }else{//不需要关联表
            ColumnInfo columnInfo = new ColumnInfo();
            if(isGernericType(field)){//一对多
                columnInfo.setClassName(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                columnInfo.setForeignKey(true);
                columnInfo.setIndex(true);
                columnInfo.setLength(11);
                columnInfo.setType("INT");
//                columnInfo.setName(clazz.getSimpleName() + "_id");
                columnInfo.setName(getForeignKey(clazz, field));
                columnInfo.setNameInClass(clazz.getName());
            }else if(!isBasicType(field)){//一对一
                columnInfo.setClassName(clazz.getName());
                columnInfo.setForeignKey(true);
                columnInfo.setIndex(true);
                columnInfo.setLength(11);
                columnInfo.setType("INT");
                columnInfo.setName(getForeignKey(clazz, field));
                columnInfo.setNameInClass(field.getType().getName());
            }
            List<ColumnInfo> list = new ArrayList<>();
            list.add(columnInfo);
            tableInfo.setColumnInfoList(list);
        }
        return tableInfo;
    }

    /**
     * 获取关联数据的外检名称
     * @param field
     * @return
     */
    private static String getForeignKey(Class clazz, Field field) {
        String res = "";
        Column column = field.getAnnotation(Column.class);
        if(column != null){
            res = column.name();
        }
        if(StringUtils.isEmpty(res)){
            res = NameUtil.toCamel(clazz.getSimpleName());
            res = NameUtil.toUnderLine(res) + "_id";
        }
        return res;
    }

    private static String getRelativeSimpleClass(Field field){
        String relateClass = "";
        if (isBasicType(field)) {
            relateClass = field.getType().getSimpleName();
        } else if (isGernericType(field)) {
            relateClass = field.getGenericType().getTypeName();
            relateClass = relateClass.substring(relateClass.lastIndexOf(".") + 1, relateClass.length() - 1);
        } else {
            relateClass = null;
        }
        return relateClass;
    }
    /**
     * 如果需要关联表的数据，根据该信息获取关联表
     * @param tmpName
     * @param tmpKey
     * @param tmpCol
     * @param tmpSort
     * @return
     */
    private static TableInfo getRelativeTable(String tmpName, String tmpKey, String tmpCol, boolean tmpSort){
        TableInfo tableInfo = new TableInfo();
        tableInfo.setName(tmpName);
        List<ColumnInfo> list = new ArrayList<>();
        ColumnInfo info = new ColumnInfo();
        info.setName(tmpKey);
        info.setType("INT");
        info.setLength(11);
        info.setIndex(true);
        list.add(info);
        ColumnInfo info1 = new ColumnInfo();
        info1.setName(tmpCol);
        info1.setType("INT");
        info1.setLength(11);
        info.setIndex(true);

        list.add(info1);
        if (tmpSort) {
            ColumnInfo info2 = new ColumnInfo();
            info2.setName("idx");
            info2.setType("INT");
            info2.setLength(5);
            info.setIndex(true);
            list.add(info2);
        }
        tableInfo.setColumnInfoList(list);
        tableInfo.setConnTable(true);
        return  tableInfo;
    }


    /**
     * 处理默认
     * 没有任何注解的字段将进行该处理
     *
     * @param field
     * @param clazz
     * @return
     */
    private static ColumnInfo getDefaultByField(Field field, Class clazz) {
        ColumnInfo info = new ColumnInfo();
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {//泛型类型
            String fieldType = ((ParameterizedType) type).getTypeName();
            info.setForeignKey(true);
            info.setClassName(fieldType);
            info.setName(clazz.getSimpleName() + "_id");
            info.setLength(11);
            info.setType("INT");
            info.setIndex(true);
        } else {//一般类型
            String name = NameUtil.toUnderLine(field.getName());
            String fieldType = field.getType().getName();
            if (fieldType.startsWith("java.")) {//基本类型
                info.setType(getDefaultTypeByField(fieldType));
                info.setLength(getDefaultLengthByType(info.getType()));
                info.setName(name);
            } else {//关联类型， 默认为主键
                info.setIndex(true);
                String simpleName = field.getType().getSimpleName();
                info.setName(NameUtil.toUnderLine(simpleName) + "_id");
                info.setLength(11);
                info.setType("INT");
            }
        }
        return info;
    }

    private static String getDefaultTypeByField(String typeClass) {
        if (Integer.class.getName().equals(typeClass)) {
            return "int";
        } else if (double.class.getName().equals(typeClass)) {
            return "double";
        } else if (float.class.getName().equals(typeClass)) {
            return "float";
        } else if (String.class.getName().equals(typeClass)) {
            return "varchar";
        } else if (Date.class.getName().equals(typeClass)) {
            return "timestamp";
        } else if (BigDecimal.class.getName().equals(typeClass)) {
            return "double";
        } else {
            return "varchar";
        }
    }

    private static int getDefaultLengthByType(String type) {
        if ("int".equals(type)) {
            return 11;
        } else if ("double".equals(type)) {
            return 10;
        } else if ("varchar".equals(type)) {
            return 255;
        } else if ("text".equals(type)) {
            return 4000;
        } else {
            return 0;
        }
    }

    /**
     * 是否是泛型类型
     *
     * @param field
     * @return
     */
    private static boolean isGernericType(Field field) {
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            return true;
        }
        return false;
    }

    /**
     * 是否为基本类型
     *
     * @param field
     * @return
     */
    private static boolean isBasicType(Field field) {
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            return false;
        }
        if (type.getTypeName().startsWith("java.")) {
            return true;
        }
        return false;
    }

    public static void gen() {
        List<TableInfo> tableInfoList = new ArrayList<>();

    }
}
