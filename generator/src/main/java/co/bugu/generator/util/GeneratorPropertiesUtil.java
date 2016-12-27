package co.bugu.generator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Administrator on 2016/3/30.
 */
public class GeneratorPropertiesUtil {

    public static Properties props = new Properties();

    static {
        InputStream is = GeneratorPropertiesUtil.class.getClassLoader().getResourceAsStream("project.properties");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(ParamterBean pb) {
        // 数据库
        pb.setIp((String) props.get("db_host"));
        pb.setUser((String) props.get("db_user"));
        pb.setPwd((String) props.get("db_pwd"));
        pb.setDb((String) props.get("db_name"));
        // 包
        pb.setRootPackName((String) props.get("pro_package"));
        pb.setBusinessName((String) props.get("pro_name"));
        pb.setTableName((String) props.get("pro_table"));
        pb.setProType((String) props.get("pro_type"));
        pb.setAlias((String)props.get("pro_alias"));

        //表前缀
        pb.setTablePrefix(props.getProperty("table_prefix"));

        // 如果不设置 获取默认目录
        pb.setCodePath(FileUtil.getAbsPath() + "/generate/src/main/java/");
        pb.setWebPath(FileUtil.getAbsPath() + "/generate/web/views/");
        pb.setResources(FileUtil.getAbsPath() + "/generate/src/main/resources/");

        // 如果不设置 获取默认目录
        pb.setCodePath(FileUtil.getAbsPath() + "/generate/src/main/java/");
        pb.setWebPath(FileUtil.getAbsPath() + "/generate/web/views/");
        pb.setResources(FileUtil.getAbsPath() + "/generate/src/main/resources/");

    }

    public static void main(String[] args) {
        GeneratorPropertiesUtil.props.get("db_ip");
    }
}


