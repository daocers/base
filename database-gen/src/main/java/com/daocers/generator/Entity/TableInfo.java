package com.daocers.generator.Entity;

import co.bugu.annotation.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daocers on 2016/9/29.
 */
public class TableInfo {
    private String name;
    private String className;
    private boolean connTable;
    private List<ColumnInfo> columnInfoList = new ArrayList<>();
    private List<String> indexList = new ArrayList<>();//索引字段


    public boolean isConnTable() {
        return connTable;
    }

    public void setConnTable(boolean connTable) {
        this.connTable = connTable;
    }

    public List<String> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<String> indexList) {
        this.indexList = indexList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }
}
