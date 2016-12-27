package co.bugu.entity;

/**
 * 记录一个类中对应的每一个字段的数据
 * Created by daocers on 2016/9/27.
 */
public class FieldInfo {
    private boolean nullable = false;//是否可为空
    private boolean PrimaryKey = false;//是否为主键
    private boolean autoIncrement = false;//是否自增
    private String name;//名称
    private String type;//类型
    private Integer length;//长度，长度没有作用的类型可以为空
    private String comment;//注释

    private Integer scale;//小数点几位
    private Integer index;//用于字段排序

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPrimaryKey() {
        return PrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        PrimaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
