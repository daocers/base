package co.bugu.gen.core;

import co.bugu.annotation.HasOne;
import co.bugu.annotation.processor.*;
import co.bugu.domain.Parent;
import co.bugu.entity.*;
import co.bugu.framework.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import com.sun.xml.internal.rngom.ast.builder.Include;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by daocers on 2016/9/27.
 */
public class Generator {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<TableInfo> list = gen("co.bugu.domain.test");
        Map<String, String> map = processConnTable(list);
        for(TableInfo tableInfo: list){
            processData(tableInfo);
            processForeign(map, tableInfo);
        }
    }


    public static Map<String, String> processConnTable(List<TableInfo> list){
        Map<String, String> map = new HashMap<>();
        for(TableInfo info: list){
            map.put(info.getClassName(), info.getName());
        }
        return map;
    }

    public static List<TableInfo> gen(String packName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<TableInfo> list = new ArrayList<>();
        Set<Class<?>> set = ReflectUtil.getClasses(packName);
        Iterator<Class<?>> iterator = set.iterator();
        while(iterator.hasNext()){
            Class clazz = iterator.next();
            TableInfo info = TableProcessor.processClass(clazz);
            info.setClassName(clazz.getName());
            info.setFieldInfoList(new ArrayList<>());
            info.setHasManyInfoList(new ArrayList<>());
            info.setHasOneInfoList(new ArrayList<>());
            info.setIndexInfoList(new ArrayList<>());
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){
                FieldInfo fieldInfo = new FieldInfo();
                boolean ignore = IgnoreParser.parseIgnore(field);
                if(!ignore){
                    HasManyInfo hasManyInfo = HasManyParser.parseHasMany(field);
                    if(hasManyInfo != null){
                        hasManyInfo.setOwnerClass(clazz.getName());
                        hasManyInfo.setOwnerName(info.getName());
                    }

                    HasOneInfo hasOneInfo = HasOneParser.parseHasOne(field);
                    if(hasManyInfo == null && hasOneInfo == null){
                        fieldInfo = ColumnParser.parseColumn(field, fieldInfo);
                        fieldInfo = IdParser.parseId(field, fieldInfo);

                        IndexInfo indexInfo = IndexParser.parseIndex(field);
                        if(indexInfo != null){
                            info.getIndexInfoList().add(indexInfo);
                        }
                        info.getFieldInfoList().add(fieldInfo);
                    }else{
                        if(hasManyInfo != null){
                            info.getHasManyInfoList().add(hasManyInfo);
                        }
                        if(hasOneInfo != null){
                            info.getHasOneInfoList().add(hasOneInfo);
                        }
                    }
                }
            }
            list.add(info);
//            processData(info);
//            System.out.println(JSON.toJSONString(info, SerializerFeature.WriteNullStringAsEmpty));
        }
        return list;

    }

    public static TableInfo processData(TableInfo info){
        Map<String, String> tablekey = new HashMap<>();
        int i = 0;

        String table = info.getName();
        StringBuffer buffer = new StringBuffer();
        StringBuffer indexBuffer = new StringBuffer();
        StringBuffer connBuffer = new StringBuffer();
        StringBuffer foreignBuffer = new StringBuffer();
        String id = "";
        buffer.append("CREATE TABLE ").append(table).append("(\n");
        for(FieldInfo fieldInfo: info.getFieldInfoList()){
            if(fieldInfo.isPrimaryKey()){
                id = fieldInfo.getName();
            }
            fieldInfo.setIndex(i++);
            buffer.append("\t")
                    .append(fieldInfo.getName())
                    .append(" ")
                    .append(fieldInfo.getType())
                    .append(fieldInfo.getLength() != null ? "(" + fieldInfo.getLength() + ")" : "")
                    .append(fieldInfo.isNullable() ? " ": " NOT NULL")
                    .append(fieldInfo.isAutoIncrement() ? " AUTO_INCREMENT" : "")
                    .append(", \n");
        }
        for(HasOneInfo hasOneInfo : info.getHasOneInfoList()){
            if(hasOneInfo.isReverse()){
                buffer.append("\t")
                        .append(hasOneInfo.getColName())
                        .append(" INT(11) NOT NULL, ")
                        .append("\n");
                indexBuffer.append("CREATE INDEX idx_")
                        .append(hasOneInfo.getColName())
                        .append(" ON ")
                        .append(table)
                        .append("(")
                        .append(hasOneInfo.getColName())
                        .append(");\n");
            }
        }
        buffer.append("\tPRIMARY KEY (")
                .append(id)
                .append(")\n");
        buffer.append(");");

        for(IndexInfo indexInfo : info.getIndexInfoList()){
            StringBuffer colBuffer = new StringBuffer();
            for(String s: indexInfo.getColumnList()){
                colBuffer.append(s).append(",");
            }
            indexBuffer.append("CREATE INDEX idx_")
                    .append(indexInfo.getName())
                    .append(" ON ")
                    .append(table)
                    .append("(")
                    .append(colBuffer.substring(0, colBuffer.length() - 1))
                    .append(");\n");
        }

        for(HasManyInfo hasManyInfo : info.getHasManyInfoList()){
            if(hasManyInfo.isNeedConnTable()){//需要关联表
                connBuffer.append("CREATE TABLE ")
                        .append(hasManyInfo.getTableName())
                        .append("(\n")
                        .append("\t")
                        .append(hasManyInfo.getKey())
                        .append(" INT (11) NOT NULL, ")
                        .append("\t")
                        .append(hasManyInfo.getColumn())
                        .append(" INT (11) NOT NULL");
                if(hasManyInfo.isSort()){
                    connBuffer.append(", \t")
                            .append("idx INT(5) ");
                }
                connBuffer.append(");\n");
            }else{//不需要关联表，在多的一方保存一的id

            }
        }

        System.out.println("*****************");
        System.out.println(buffer.toString());
        System.out.println(indexBuffer.toString());
        String str = "CREATE TABLE table_test(" +
                "\tid INT NOT NULL AUTO_INCREMENT,\n" +
                "\tNAME VARCHAR(200) NOT NULL,\n" +
                "\tprice DOUBLE(8,2) NOT NULL,\n" +
                "\tcreate_time TIMESTAMP,\n" +
                "\tPRIMARY KEY (id)\n" +
                ");" ;
        return null;

    }

    private static void processForeign(Map<String, String> data, TableInfo tableInfo){
        StringBuffer foreignBuffer = new StringBuffer();
        StringBuffer indexBuffer = new StringBuffer();
        for(HasManyInfo hasManyInfo: tableInfo.getHasManyInfoList()){
            if(!hasManyInfo.isNeedConnTable()){
                String inTable = data.get(hasManyInfo.getFollowClass());
                String inCol = hasManyInfo.getKey();
                foreignBuffer.append("ALTER TABLE ")
                        .append(inTable)
                        .append(" ADD ")
                        .append(inCol)
                        .append(" INT (11);\n");
                indexBuffer.append("CREATE INDEX idx_")
                        .append(inCol)
                        .append(" ON ")
                        .append(inTable)
                        .append("(")
                        .append(inCol)
                        .append(");\n");
            }

        }

        System.out.println("--------------------------");
        System.out.println(foreignBuffer);
        System.out.println(indexBuffer);
        System.out.println("--------------------------");


    }



}
