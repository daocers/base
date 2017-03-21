package co.bugu.db.annotation.domain;

/**
 * Created by user on 2017/3/21.
 */
public class ColumnInfo {
//    名称
    private String name;
    //类型
    private String type;
    //长度
    private Integer length;
    //小数位长度
    private Integer decimal;
    //是否能为空
    private Boolean nullable;
    //是否为id
    private Boolean isId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean getId() {
        return isId;
    }

    public void setId(Boolean id) {
        isId = id;
    }
}
