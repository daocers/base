package co.bugu.generator.domain;

import java.util.List;

/**
 * 类对应的信息
 * Created by daocers on 2016/10/10.
 */
public class ClassInfo {
    private String name;
    private String tableName;
    private List<FieldInfo> fieldList;
    private boolean isConnTable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FieldInfo> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldInfo> fieldList) {
        this.fieldList = fieldList;
    }

    public boolean isConnTable() {
        return isConnTable;
    }

    public void setConnTable(boolean connTable) {
        isConnTable = connTable;
    }
}
