package com.daocers.generator.Entity;

/**
 * 记录表的字段信息
 * Created by daocers on 2016/9/29.
 */
public class ColumnInfo {
    private String tableName;
    private String name;
    private String type;
    private int length;
    private String comment;
    private boolean id;
    private boolean autoCreament;
    private boolean index;
    private boolean foreignKey;
    private String className;
    private String nameInClass;

    public String getNameInClass() {
        return nameInClass;
    }

    public void setNameInClass(String nameInClass) {
        this.nameInClass = nameInClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isAutoCreament() {
        return autoCreament;
    }

    public void setAutoCreament(boolean autoCreament) {
        this.autoCreament = autoCreament;
    }

    public boolean isIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }
}
