package co.bugu.wechat.global;

import co.bugu.framework.core.util.BuguProperties;
import co.bugu.framework.core.util.BuguPropertiesUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by daocers on 2016/7/28.
 */
public class Constant {

    private static Map<String, String> data = new HashMap<>();

//    private static Map<String, String> systemData = new HashMap<>();

    static{
        try {
            BuguProperties properties = BuguPropertiesUtil.load("conf/interface_url.properties");
            Iterator<Object> iter  = properties.getProperties().keySet().iterator();
            while(iter.hasNext()){
                String key = (String) iter.next();
                data.put(key, properties.get(key));
            }
            properties = BuguPropertiesUtil.load("conf/wechat.properties");
            iter = properties.getProperties().keySet().iterator();
            while(iter.hasNext()){
                String key = (String) iter.next();
                data.put(key, properties.get(key));
            }
            properties = BuguPropertiesUtil.load("conf/system.properties");
            iter = properties.getProperties().keySet().iterator();
            while(iter.hasNext()){
                String key = (String) iter.next();
                data.put(key, properties.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if(data.containsKey(key)){
            return data.get(key);
        }else{
            return "";
        }
    }
}
