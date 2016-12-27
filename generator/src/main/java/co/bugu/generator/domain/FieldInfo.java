package co.bugu.generator.domain;

/**
 * Created by daocers on 2016/10/10.
 * 类中的字段信息
 */
public class FieldInfo {
    private String className;//所在类的名称，simpleName
    private String name;//类中对应的名称
    private String dbName;//数据库中对应的名称
    private boolean isPrimaryKey;//是否主键
    private boolean isIndex;//是否索引字段
    private String dbType;//数据库中对应的类型
    private Integer length;//数据库中对应的长度
    private boolean isGeneric;//是否是泛型类型（如果是泛型类型，默认为集合类型，需要关联表或者关联数据库中记录表数据）
    private String actualClassName;//关联的类名称， simpleName
    private boolean isInThis;//该字段的数据库信息是否在该类中

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isIndex() {
        return isIndex;
    }

    public void setIndex(boolean index) {
        isIndex = index;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public boolean isGeneric() {
        return isGeneric;
    }

    public void setGeneric(boolean generic) {
        isGeneric = generic;
    }

    public String getActualClassName() {
        return actualClassName;
    }

    public void setActualClassName(String actualClassName) {
        this.actualClassName = actualClassName;
    }

    public boolean isInThis() {
        return isInThis;
    }

    public void setInThis(boolean inThis) {
        isInThis = inThis;
    }
}
