package co.bugu.pay.constant;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by daocers on 2016/9/22.
 */
public class AlipayConstant {

    private static Logger logger = LoggerFactory.getLogger(AlipayConstant.class);

    private static Map<String, String> data = new HashMap<>();

    static {
        InputStream is = AlipayConstant.class.getClassLoader()
                .getResourceAsStream("conf/alipay.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            Set<Object> set = properties.keySet();
            Iterator<Object> iter = set.iterator();
            while(iter.hasNext()){
                String key = (String) iter.next();
                data.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            logger.error("加载配置文件失败", e);
        }
    }

    public static String get(String key){
        return data.get(key);
    }

    public static Integer getInt(String key){
        try{
            String tmp = data.get(key);
            if(StringUtils.isEmpty(tmp)){
                return null;
            }else{
                return Integer.valueOf(tmp);
            }
        }catch (Exception e){
            logger.error("获取配置参数失败", e);
            return null;
        }
    }
}
