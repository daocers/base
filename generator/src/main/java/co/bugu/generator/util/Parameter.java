package co.bugu.generator.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by daocers on 2016/7/31.
 * 参数domain类
 */
public class Parameter {

    // 类
    private String rootPackName;// 包名com.google
    private String businessName;// 业务名称map

    // 页面
    private String webDir;// 页面根目录
    private String prefix;// 页面前缀

    // 数据库
    private String host;
    private String user;
    private String pwd;
    private String db;
    private String alias;//数据库表别名

    // 生成
    private String codePath;
    private String webPath;
    private String resources;

    //类别
    private String proType;

    private String dbPrefix;    //数据库前缀
    private String tableName;   //表名
    private String className;   //类名
    private String variableName;  //变量名
    private String nsName;//映射文件命名空间

    private String apiVersion;// api版本号

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getDbPrefix() {
        if(db == null){
            dbPrefix = "";
        }
        return dbPrefix;
    }

    public void setDbPrefix(String dbPrefix) {
        this.dbPrefix = dbPrefix;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getCodePath() {
        if (StringUtils.isBlank(codePath)) {
            return FileUtil.getAbsPath() + "generate/src/";
        }
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getWebPath() {
        if (StringUtils.isBlank(webPath)) {
            return FileUtil.getAbsPath() + "generate/web/";
        }
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getFullPack() {
        return rootPackName + "." + businessName;
    }

    public String getRootPackName() {
        return rootPackName;
    }

    public void setRootPackName(String rootPackName) {
        this.rootPackName = rootPackName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWebDir() {
        if (StringUtils.isBlank(webDir)) {
            return tableName;
        } else {
            return webDir;
        }
    }

    public void setWebDir(String webDir) {
        this.webDir = webDir;
    }

    public String getPrefix() {
        if (StringUtils.isBlank(prefix)) {
            return tableName;
        } else {
            return prefix;
        }
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }


}
