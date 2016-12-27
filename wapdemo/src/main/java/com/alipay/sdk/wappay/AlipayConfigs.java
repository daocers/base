package com.alipay.sdk.wappay;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class AlipayConfigs {
    private static Properties prop;

    //    static {
    //        //TODO 需要修改资源文件获取方式，资源文件应从SDK外获取
    //        prop = new Properties();
    //        try {
    //            InputStream in = AlipayConfigs.class.getResourceAsStream("alipay_configs.properties");
    //            prop.load(in);
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }

    public static void loadConfig(String path, Boolean fourceInit) {
        if (APP_ID != null && !fourceInit) {
            return;
        }
        prop = new Properties();

        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(path));
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        APP_ID = prop.getProperty("app_id");
        PRIVATE_KEY = prop.getProperty("private_key");
        ALIPAY_PUBLIC_KEY = prop.getProperty("alipay_public_key");
        GATEWAY_URL = prop.getProperty("gateway_url");
        CHARSET = prop.getProperty("charset");
        RETURN_URL = prop.getProperty("return_url");
        NOTIFY_URL = prop.getProperty("notify_url");
        SELLER_ID = prop.getProperty("seller_id");
        TIMEOUT_EXPRESS = prop.getProperty("timeout_express");
        DEBUG_LOG = prop.getProperty("debug_log");
    }

    public static String APP_ID            = null;
    public static String PRIVATE_KEY       = null;
    public static String ALIPAY_PUBLIC_KEY = null;
    public static String GATEWAY_URL       = null;
    public static String CHARSET           = null;
    public static String RETURN_URL        = null;
    public static String NOTIFY_URL        = null;
    public static String SELLER_ID         = null;
    public static String TIMEOUT_EXPRESS   = null;
    public static String DEBUG_LOG         = null;
}
