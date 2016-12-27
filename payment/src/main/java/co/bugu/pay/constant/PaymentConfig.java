package co.bugu.pay.constant;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * 记录支付的配置信息
 * 根据支付方式不同，前面会带上前缀ali_或者wx_
 *
 * Created by daocers on 2016/9/27.
 */
public class PaymentConfig {

    private static Logger logger = LoggerFactory.getLogger(PaymentConfig.class);

    private static Map<String, String> data = new HashMap<>();

    static {
        try {
            InputStream is = AlipayConstant.class.getClassLoader()
                    .getResourceAsStream("conf/alipay.properties");
            Properties properties = new Properties();
            properties.load(is);
            Set<Object> set = properties.keySet();
            Iterator<Object> iter = set.iterator();
            while(iter.hasNext()){
                String key = (String) iter.next();
                data.put("ali_" + key, properties.getProperty(key));
            }
            is = AlipayConstant.class.getClassLoader()
                    .getResourceAsStream("conf/wxpay.properties");
            properties = new Properties();
            properties.load(is);
            iter = set.iterator();
            while(iter.hasNext()){
                String key = (String) iter.next();
                data.put("wx_" + key, properties.getProperty(key));
            }
        } catch (IOException e) {
            logger.error("加载配置文件失败", e);
        }
    }

    private static String get(String key){
        return data.get(key);
    }

    private static Integer getInt(String key){
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

    public static String getWx(String key){
        return data.get("wx_" + key);
    }

    public static String getAli(String key){
        return data.get("ali_" + key);
    }
}
