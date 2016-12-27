package co.bugu.entity;

import java.util.List;

/**
 * Created by daocers on 2016/9/24.
 */
public class TableInfo {
    private String className;
    private String name;
    private String comment;
    private List<FieldInfo> fieldInfoList;
    private List<HasManyInfo> hasManyInfoList;
    private List<HasOneInfo> hasOneInfoList;
    private List<IndexInfo> indexInfoList;
    private List<String> foreignKey;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(List<String> foreignKey) {
        this.foreignKey = foreignKey;
    }

    public List<HasManyInfo> getHasManyInfoList() {
        return hasManyInfoList;
    }

    public void setHasManyInfoList(List<HasManyInfo> hasManyInfoList) {
        this.hasManyInfoList = hasManyInfoList;
    }

    public List<IndexInfo> getIndexInfoList() {
        return indexInfoList;
    }

    public void setIndexInfoList(List<IndexInfo> indexInfoList) {
        this.indexInfoList = indexInfoList;
    }

    public List<HasOneInfo> getHasOneInfoList() {
        return hasOneInfoList;
    }

    public void setHasOneInfoList(List<HasOneInfo> hasOneInfoList) {
        this.hasOneInfoList = hasOneInfoList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

}
