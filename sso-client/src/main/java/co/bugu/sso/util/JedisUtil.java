package co.bugu.sso.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by daocers on 2016/5/26.
 */
public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private static JedisPool pool = null;

    static {
        Properties properties = null;
        try {
            properties = PropertiesUtil.load("conf/redis.properties");
        } catch (IOException e) {
            logger.error("[jedisUtil 初始化异常], 读取配置文件失败", e);
        }

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            Integer maxIdle = Integer.valueOf(properties.getProperty("redis.maxIdle", "50"));
            Integer maxTotal = Integer.valueOf(properties.getProperty("redis.maxTotal", "200"));
            Integer maxWaitMillis = Integer.valueOf(properties.getProperty("redis.maxWaitMillis", "5000"));
            config.setMaxIdle(maxIdle);
            config.setMaxTotal(maxTotal);
            config.setMaxWaitMillis(maxWaitMillis);

            String host = properties.getProperty("redis.host");
            Integer port = Integer.valueOf(properties.getProperty("redis.port", "6379"));
            Integer timeout = Integer.valueOf(properties.getProperty("redis.timeout", "1000"));
            String password = properties.getProperty("redis.password", "");
            Integer database = Integer.valueOf(properties.getProperty("redis.db", "0"));
            pool = new JedisPool(config, host, port, timeout, password, database);
        } catch (Exception e) {
            logger.error("[jedis初始化异常]， 初试化jedisPool异常", e);
        }
        logger.info("[jedis 初始化完成] ......");
    }

    public static JedisPool getJedisPool() {
        return pool;
    }

    public static void set(String key, String value) {

    }

    public static void set(String key, Object object) {

    }

    public static String get(String key) {
        return null;
    }

    public static boolean isExist(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.exists(key);
        }catch (Exception e){
            logger.error("[jedis异常] exists", e);
            throw new Exception("jedis异常", e);
        }finally {
            release(jedis);
        }
    }


    /**
     * 释放jedis回pool
     *
     * @param jedis
     */
    private static void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 设置超时时间
     * @param key 单位是秒
     * @param second
     */
    public static void expire(String key, int second) {
        Jedis jedis = null;
        try{
            jedis.expire(key, second);
        }catch (Exception e){
            logger.error("jedis setExpire失败",e);
        }finally {
            release(jedis);
        }
    }
}
