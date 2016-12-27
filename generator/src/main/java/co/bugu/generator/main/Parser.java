package co.bugu.generator.main;

import co.bugu.framework.core.util.ReflectUtil;
import co.bugu.generator.domain.ClassInfo;
import co.bugu.generator.domain.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by daocers on 2016/10/10.
 */
public class Parser {
    public static void main(String[] args){

    }

    /**
     * 解析class，找出对应的table信息
     * @param pack
     * @return
     */
    public static List<ClassInfo> getClassInfo(String pack){
        List<ClassInfo> classInfoList = new ArrayList<>();
        Set<Class<?>> classSet = ReflectUtil.getClasses(pack);
        for(Class clazz: classSet){
            ClassInfo classInfo = new ClassInfo();
            classInfo.setName(clazz.getName());
            classInfo.setTableName(null);
            classInfo.setConnTable(false);
            classInfo.setFieldList(new ArrayList<>());
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){
                FieldInfo fieldInfo = new FieldInfo();
                Type fieldType = field.getGenericType();
                fieldInfo.setClassName(clazz.getName());
                fieldInfo.setName(field.getName());

                if(fieldType instanceof ParameterizedType){//泛型类型
                    ParameterizedType type = (ParameterizedType) fieldType;
                    String acturalType = type.getActualTypeArguments()[0].getTypeName();
                    fieldInfo.setGeneric(true);
                    fieldInfo.setActualClassName(acturalType);
                }else{//非泛型
                    fieldInfo.setGeneric(false);
                }

//                此处处理fieldInfo的其他信息
                fieldInfo.setDbName(null);
                fieldInfo.setDbType(null);
                fieldInfo.setLength(null);
//                fieldInfo.setIndex(null);
//                fieldInfo.setInThis(null);
//                fieldInfo.setPrimaryKey(null);

                classInfo.getFieldList().add(fieldInfo);
            }

            classInfoList.add(classInfo);
        }
        return classInfoList;
    }
}
