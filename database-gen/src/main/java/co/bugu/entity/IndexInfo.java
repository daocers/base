package co.bugu.entity;

import java.util.List;

/**
 * Created by daocers on 2016/9/24.
 */
public class IndexInfo {
    private String name;
    private List<String> columnList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }
}
