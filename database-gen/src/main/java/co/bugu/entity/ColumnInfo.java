package co.bugu.entity;

/**
 * Created by daocers on 2016/9/24.
 */
public class ColumnInfo {
    private String name;
    private String type;
    private Integer length;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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
}
