package co.bugu.entity;

/**
 * 记录关联表信息
 * Created by daocers on 2016/9/27.
 */
public class HasManyInfo {
    private String ownerClass;
    private String ownerName;
    private String followClass;

    private String tableName;
    private String key;//关联表的key
    private String column;//关联表的列
    private boolean sort;//是否排序
    private boolean needConnTable;//是否需要关联表

    public String getFollowClass() {
        return followClass;
    }

    public void setFollowClass(String followClass) {
        this.followClass = followClass;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(String ownerClass) {
        this.ownerClass = ownerClass;
    }

    public boolean isNeedConnTable() {
        return needConnTable;
    }

    public void setNeedConnTable(boolean needConnTable) {
        this.needConnTable = needConnTable;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }
}
