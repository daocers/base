package co.bugu.sso.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by daocers on 2016/6/28.
 */
public class GlobalConstant {
    private static Logger logger = LoggerFactory.getLogger(GlobalConstant.class);

    private static String AUTH_SERVER;

    private static Integer EXPIRE_TIME;

    static {
        InputStream is = GlobalConstant.class.getClassLoader().getResourceAsStream("conf/system.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            AUTH_SERVER = properties.getProperty("auth-server");
            String info = properties.getProperty("expire-time");
            EXPIRE_TIME = Integer.valueOf(info) * 60;
        } catch (IOException e) {
            logger.error("[加载配置文件失败]", e);
        }

    }

    public static String getAuthServer() {
        return AUTH_SERVER;
    }

    public static Integer getExpireTime() {
        return EXPIRE_TIME;
    }

}
