package co.bugu.sso.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by daocers on 2016/6/29.
 */
public class GlobalConstant {

    private static Properties properties;

    private static Logger logger = LoggerFactory.getLogger(GlobalConstant.class);

    static {
        InputStream is = GlobalConstant.class.getClassLoader().getResourceAsStream("conf/system.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            logger.error("[加载配置文件失败]\n *******初始化配置文件失败******", e);
        }

    }

    public static String get(String key){
        if(properties.containsKey(key)){
            return properties.getProperty(key);
        }else{
            return null;
        }
    }

    public static Integer getInt(String key){
        if(properties.containsKey(key)){
            return Integer.valueOf(properties.getProperty(key));
        }else{
            return null;
        }
    }

    public static Boolean getBool(String key){
        if(properties.containsKey(key)){
            return properties.getProperty(key).equals("true");
        }else{
            return null;
        }
    }
}
