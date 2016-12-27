package co.bugu.generator;

import co.bugu.generator.util.*;
import co.bugu.framework.core.util.BuguProperties;
import co.bugu.framework.core.util.BuguPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by daocers on 2016/5/30.
 * 代码生成工具主类
 */
public class BuguGenMain {

    public static void main(String[] args) throws Exception {
        //清空历史目录
        DeleteDirectory.deleteDir(new File(FileUtil.getAbsPath() + "generate/"));
        List<Map<String, String>> list = processTableInfo();
        for (Map<String, String> info : list) {
//            加载参数
            Parameter param = initParameter(info);
//            生成配置文件
            generateConfigXml(param);
//            生成代码
            generateCode();
//              生成代码后处理
            afterProcess(param);
        }


    }

    /**
     * 初始化参数
     * 解析table.properties文件
     *
     * @param
     * @throws IOException
     */
    public static List<Map<String, String>> processTableInfo() throws IOException {
        List<Map<String, String>> info = new ArrayList<>();
        BuguProperties properties = BuguPropertiesUtil.load("table.properties");
        Enumeration<?> enumeration = properties.getProperties().propertyNames();
        while (enumeration.hasMoreElements()) {
            Map<String, String> map = new HashMap<>();
            String name = (String) enumeration.nextElement();
            map.put("table", name);
            map.put("alias", properties.get(name));
            info.add(map);
        }

        return info;
    }

    /**
     * 初始化参数
     * 解析project.properties文件
     *
     * @param info
     * @throws IOException
     */
    public static Parameter initParameter(Map<String, String> info) throws IOException {
        BuguProperties props = BuguPropertiesUtil.load("project.properties");
        Parameter para = new Parameter();
        String table = info.get("table");
        String alias = info.get("alias");

        para.setAlias(alias);
        para.setTableName(table);

        para.setDbPrefix(props.get("db_table_prefix"));

        // 数据库
        para.setHost(props.get("db_host"));
        para.setUser(props.get("db_user"));
        para.setPwd(props.get("db_pwd"));
        para.setDb(props.get("db_name"));

        // 包
        para.setRootPackName(props.get("pro_package"));
        para.setBusinessName(props.get("pro_name"));
        para.setProType(props.get("pro_type"));

        para.setNsName(props.get("mapper_namespace"));
        if(StringUtils.isEmpty(para.getNsName())){
            para.setNsName(para.getBusinessName());//不指定命名空间前缀，使用工程名
        }

        String apiVersion = props.get("api_version");
        if(StringUtils.isEmpty(apiVersion)){
            apiVersion = "";
        }else{
            apiVersion = apiVersion.replaceAll("[.]", "_");
        }
        para.setApiVersion(apiVersion);

        // 如果不设置 获取默认目录
        para.setCodePath(FileUtil.getAbsPath() + "/generate/src/main/java/");
        para.setWebPath(FileUtil.getAbsPath() + "/generate/web/views/");
        para.setResources(FileUtil.getAbsPath() + "/generate/src/main/resources/");

        String tableName = para.getTableName().replace(para.getDbPrefix(), "");
        if(tableName.startsWith("_")){
            tableName = tableName.substring(1);
        }
        String className = GenStringUtil.toUpperCase(tableName);

        para.setClassName(className);
        para.setVariableName(GenStringUtil.toVariableName(className));
        return para;
    }


    /**
     * 生成generator的配置xml文件
     *
     * @param pb
     * @throws Exception
     */
    public static void generateConfigXml(Parameter pb) throws Exception {

        //生成JAVA编码
        CodeFactory.production(pb);

        //生成generatorConfig.xml
        XmlUtil.createMBGXml(pb);
    }


    /**
     * 生成代码
     *
     * @throws Exception
     */
    public static void generateCode() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "generatorConfig.xml";
        File configFile = new File(FileUtil.getAbsPath() + "/src/main/resources/" + genCfg);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void afterProcess(Parameter pb) {
        // 整理mapper
        String codePackPath = pb.getCodePath() + GenStringUtil.toPkgPath(pb.getFullPack());
        XmlUtil.reloadMapperXml(codePackPath + "/dao/", pb.getResources() + "/mapper/", pb.getClassName() + "Mapper.xml", pb);

        //删除原xml
        FileUtil.del(codePackPath + "/dao/" + GenStringUtil.toUpperCase(pb.getTableName()) + "Mapper.xml");

    }
}
