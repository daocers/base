package co.bugu.entity;

/**
 * Created by daocers on 2016/9/27.
 */
public class HasOneInfo {
    private String className;
    private String colName;
    private boolean isReverse;

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
