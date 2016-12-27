package co.bugu.generator.util;

import org.apache.commons.lang.StringUtils;

/**
 * 参数对象
 *
 * @author Administrator
 */
public class ParamterBean {

    // 类
    private String rootPackName;// 包名com.google
    private String businessName;// 业务名称map

    // 页面
    private String webDir;// 页面根目录
    private String prefix;// 页面前缀

    // 数据库
    private String ip;
    private String user;
    private String pwd;
    private String db;

    private String alias;

    // 生成
    private String codePath;
    private String webPath;
    private String resources;

    //类别
    private String proType;

    //映射命名空间
    private String mapperNameSpace;

//   数据库表前缀
    private String tablePrefix;


    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
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

    private String tableName;// t_maps

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getMapperNameSpace() {
        return mapperNameSpace;
    }

    public void setMapperNameSpace(String mapperNameSpace) {
        this.mapperNameSpace = mapperNameSpace;
    }
}
