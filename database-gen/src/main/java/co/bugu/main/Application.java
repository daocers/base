package co.bugu.main;

import co.bugu.annotation.Column;
import co.bugu.annotation.Id;
import co.bugu.annotation.Index;
import co.bugu.entity.*;
import co.bugu.framework.core.util.ReflectUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by daocers on 2016/9/24.
 */
public class Application {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        String packageName = Application.class.getPackage().getName();
        String targetPackage = packageName.substring(0, packageName.lastIndexOf(".")) + ".domain";
        Set<Class<?>> classSet = ReflectUtil.getClasses(targetPackage);
        List<Param> paramList = new ArrayList<>();
        for(Class clazz: classSet){
            Param param = new Param();

            Annotation[] annotations = clazz.getAnnotations();
//            处理table类型
            for(Annotation annotation: annotations){
                Class<? extends Annotation> annotationClass = annotation.annotationType();
                Method[] methods = annotation.annotationType().getDeclaredMethods();
                for(Method method: methods){
                    String value = (String) method.invoke(annotation, null);
                    //处理table类型
                    TableInfo info = new TableInfo();
                    info.setName(value);
                    param.setTableInfo(info);
                }
            }

            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){

                annotations = field.getAnnotations();
                for(Annotation annotation: annotations){
                    Class annotationClass = annotation.annotationType();

                }
                field.getAnnotation(Id.class);
                Annotation annotation = field.getAnnotation(Id.class);
                annotation.annotationType().getDeclaredMethods();
            }
        }
    }

    /**
     * 获取id信息，
     * 默认主键为id
     * @param field
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static ColumnInfo processId(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Id id = field.getAnnotation(Id.class);
        Method method = id.annotationType().getDeclaredMethod("value");
        String value = (String) method.invoke(id, null);
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setName(value);
        columnInfo.setType("pk");
        return columnInfo;
    }

    /**
     * 处理关联表信息
     * @param field
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
//    public static JoinInfo processJoinInfo(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        String sort, key, column, table;
//        JoinTable join = field.getAnnotation(JoinTable.class);
//        Method method = join.annotationType().getDeclaredMethod("value");
//        String value = (String) method.invoke(join, null);
//
//        method = join.annotationType().getDeclaredMethod("key");
////        处理默认key
//        if(method == null){
//            key = "id";
//        }else{
//            key = (String) method.invoke(join, null);
//        }
//
//        method = join.annotationType().getDeclaredMethod("column");
//        if(method == null){
//            column = "o_id";
//        }else{
//            column = (String) method.invoke(join, null);
//        }
//
//        method = join.annotationType().getDeclaredMethod("table");
//        if(method == null){
//            table = "o_id";
//        }else{
//            table = (String) method.invoke(join, null);
//        }
//
//        method = join.annotationType().getDeclaredMethod("sort");
//        if(method == null){
//            sort = null;
//        }else{
//            sort = (String) method.invoke(join, null);
//            if(StringUtils.isEmpty(sort)){
//                sort = "idx";
//            }
//        }
//
//        JoinInfo joinInfo = new JoinInfo();
//        joinInfo.setColumn(column);
//        joinInfo.setTable(table);
//        joinInfo.setKey(key);
//        joinInfo.setSort(sort);
//        return joinInfo;
//    }

    /**
     * 处理索引信息
     * @param field
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static IndexInfo processIndex(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Index index = field.getAnnotation(Index.class);
        Method method = index.annotationType().getDeclaredMethod("value");
        String value = (String) method.invoke(index, null);
        if(StringUtils.isEmpty(value)){
            value = "idx_" + field.getName();
        }else{
            if(!value.startsWith("idx_")){
                value = "idx_" + value;
            }
        }
        method = index.annotationType().getDeclaredMethod("columns");
        List<String> colList = new ArrayList<>();

        if(method == null){
            colList.add(field.getName());
        }
        String columns = (String) method.invoke(index, null);
        String[] cols = columns.split(",");
        for(String col: cols){
            colList.add(col.trim());
        }
        IndexInfo indexInfo = new IndexInfo();
        indexInfo.setName(value);
        indexInfo.setColumnList(colList);
        return indexInfo;
    }

    public static ColumnInfo processColumn(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ColumnInfo columnInfo = new ColumnInfo();
        Column column = field.getAnnotation(Column.class);
        Method valueMethod = column.annotationType().getDeclaredMethod("value");
        Method typeMethod = column.annotationType().getMethod("type");
        Method lengthMethod = column.annotationType().getMethod("length");
        if(valueMethod == null){
            columnInfo.setName(getDefaultName(field.getName()));
        }else {
            String value = (String) valueMethod.invoke(column, null);
            columnInfo.setName(value);
        }
        if(typeMethod == null){
            columnInfo.setType("varchar");
        }else{
            String type = (String) typeMethod.invoke(column, null);
            columnInfo.setName(type);
        }

        if(lengthMethod == null){
            String type = columnInfo.getType();
            Integer length = null;
            if("varchar".equals(type)){
                length = 255;
            }else if("char".equals(type)){
                length = 255;
            }else if("timestamp".equals(type)){
                length = null;
            }else if("int".equals(type)){
                length = 11;
            }else if("text".equals(type)){
                length = 4000;
            }

            columnInfo.setLength(length);
        }else{
            Integer length = Integer.valueOf((String) lengthMethod.invoke(column, null));
            columnInfo.setLength(length);
        }

        return columnInfo;
    }

    /**
     * 根据字段名称获取对应的字段名
     * @param name
     * @return
     */
    private static String getDefaultName(String name) {
        if(StringUtils.isEmpty(name)){
            return null;
        }
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        StringBuilder builder = new StringBuilder();
        for(char c : name.toCharArray()){
            if(c >= 'A' && c <= 'Z'){
                builder.append("_")
                        .append(c);
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
