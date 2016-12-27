package co.bugu.entity;

import java.util.List;

/**
 * Created by daocers on 2016/9/24.
 */
public class Param {
    private TableInfo tableInfo;
    private List<IndexInfo> indexInfoList;
    private List<ColumnInfo> columnInfoList;

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public List<IndexInfo> getIndexInfoList() {
        return indexInfoList;
    }

    public void setIndexInfoList(List<IndexInfo> indexInfoList) {
        this.indexInfoList = indexInfoList;
    }

    public List<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }
}
