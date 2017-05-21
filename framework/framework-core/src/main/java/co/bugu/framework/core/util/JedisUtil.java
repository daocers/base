package co.bugu.framework.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/5/17.
 */
public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private static JedisPool pool;

    static {
        InputStream inputStream = JedisPool.class.getClassLoader().getResourceAsStream("conf/redis.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("[redis配置文件加载失败]", e);
        }

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));
            config.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));
            config.setMaxWaitMillis(Integer.parseInt(properties.getProperty("redis.maxWaitMillis")));

            pool = new JedisPool(config, properties.getProperty("redis.host"),
                    Integer.parseInt(properties.getProperty("redis.port", "6379")),
                    Integer.parseInt(properties.getProperty("redis.timeout", "5000")),
                    properties.getProperty("redis.password"),
                    Integer.parseInt(properties.getProperty("redis.db")));
        } catch (Exception e) {
            logger.error("[初始化jedisPool失败]", e);
            pool = null;
        }
    }

    /**
     * 释放资源
     *
     * @param jedis
     */
    public static void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public static Jedis getJedis() {
        try {
            if (pool != null) {
                Jedis Jedis = pool.getResource();
                return Jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("jedis 异常", e);
            return null;
        }
    }


/**===========================================*/
    /**
     * key 操作
     */


    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            logger.error("jedis  异常", e);
        } finally {
            release(jedis);
        }
    }


    public static void delObj(Object obj){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String key = obj.getClass().getName() + "_" + ReflectUtil.get(obj, "id");
            jedis.del(key);
        } catch (Exception e) {
            logger.error("jedis delObj 失败", e);
        }finally {
            release(jedis);
        }

    }

    public static Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("jedis  异常", e);
            return null;
        } finally {
            release(jedis);
        }
    }

    public static Long expire(String key, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error("jedis  异常", e);
            return null;
        } finally {
            release(jedis);
        }
    }

    public static Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.keys(pattern);
        } catch (Exception e) {
            logger.error("jedis  异常", e);
            return null;
        } finally {
            release(jedis);
        }
    }


    /**
     * 字符操作
     */
    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("jedis  异常", e);

        } finally {
            release(jedis);
        }
    }

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("jedis  异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    public static List<String> mGet(String... key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.mget(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    public static Long incrBy(String key, int add) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.incrBy(key, add);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    public static Long decrBy(String key, int minus) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.decrBy(key, minus);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * key 不存在的时候设置key的值，成功返回1，失败返回0
     */
    public static Boolean setNX(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.setnx(key, value) == 1;
        } catch (Exception e) {
            logger.error("jedis setNx 异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    /***
     *
     * hash 操作
     * */

    public static Long hDel(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hdel(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    public static Long hSet(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    public static String hGet(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * hash 获取全部数据
     *
     * @param key
     * @return
     */
    public static Map<String, String> hGetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 获取多个值
     *
     * @param key
     * @param field
     * @return
     */
    public static List<String> hmGet(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hmget(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * hash  设置
     *
     * @param key
     * @param data
     * @return
     */
    public static Boolean hmSet(String key, Map<String, String> data) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hmset(key, data).equals("OK");
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    /***
     * list 操作
     *
     * */

    /**
     * 返回list长度
     *
     * @param key
     * @return
     */
    public static Long lLen(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.llen(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 移除列表第一个元素
     *
     * @param key
     * @return
     */
    public static String lPop(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpop(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 向列表头部插入值
     * 列表不存在的时候会创建
     *
     * @param key
     * @param field
     * @return
     */
    public static Long lPush(String key, String... field) {
        if(field == null || field.length == 0){
            return null;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 从列表尾部添加数据
     *
     * @param key
     * @param field
     * @return
     */
    public static Long rPush(String key, String... field) {
        if(field == null || field.length == 0){
            return null;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.rpush(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 向列表头部插入值
     * 列表不存在的时候操作无效
     *
     * @param key
     * @param field
     * @return
     */
    public static Long lPushX(String key, String... field) {
        if(field == null || field.length == 0){
            return null;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpushx(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 为已存在的list添加元素，如果不存在，操作无效
     *
     * @param key
     * @param field
     * @return
     */
    public static Long rPushX(String key, String... field) {
        if(field == null || field.length == 0){
            return null;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.rpushx(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 获取列表指定位置的元素
     *
     * @param key
     * @param
     * @return
     */
    public static List<String> lRange(String key, int begin, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lrange(key, begin, end);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 获取全部的list元素
     *
     * @param key
     * @return
     */
    public static List<String> getAllList(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            logger.error("jedis 异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 设置第index个元素的值为value
     *
     * @param key
     * @param index
     * @return
     */
    public static String lSet(String key, Integer index, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lset(key, index, value);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    /******
     *  set 操作
     *
     * */

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public static Long sAdd(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 获取集合的成员数量
     */
    public static Long sCard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 求集合差集
     * key 的顺序会影响最后的结果
     * 返回前一个set中未在后一个set出现的元素
     *
     * @param key
     * @return
     */
    public static Set<String> sDiff(String... key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sdiff(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    /**
     * 求交集
     *
     * @param key
     * @return
     */
    public static Set<String> sInter(String... key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sinter(key);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    /**
     * 是否是set成员
     *
     * @param key
     * @param field
     * @return
     */
    public static Boolean sIsMembers(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 随机返回指定数量的元素
     *
     * @param key
     * @param count
     * @return
     */
    public static List<String> sRandMember(String key, Integer count) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srandmember(key, count);
        } catch (Exception e) {
            logger.error("jedis 异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 移除set中的字段
     *
     * @param key
     * @param field
     * @return
     */
    public static Long sRem(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key, field);
        } catch (Exception e) {
            logger.error("[jedis异常] set", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 将在redis的安装目录中创建dump.rdb文件
     */
    public static void save() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.save();
        } catch (Exception e) {
            logger.error("jedis save 失败", e);
        } finally {
            release(jedis);
        }
    }


    public static void putObject(String key, Object obj) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hmset(key, getValueMap(obj));
        } catch (Exception e) {
            logger.error("[jedis异常] putObject", e);
        } finally {
            release(jedis);
        }
    }

    public static Map<String, String> getValueMap(Object obj) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o = fields[i].get(obj);
                if (o != null) {
                    if (o instanceof Date) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        map.put(varName, dateFormat.format((Date) o));
                    } else {
                        map.put(varName, o.toString());
                    }

                }
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                logger.error("[jedis 异常] getValueMap", ex);
            } catch (IllegalAccessException ex) {
                logger.error("[jedis异常] getValueMap", ex);
            }
        }
        return map;
    }

    /**
     * 将实体类放入到redis上
     *
     * @param key
     * @param object
     */
    public static void setJson(String key, Object object) {
        Jedis jedis = null;
        try {
            String res = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss:sss", SerializerFeature.PrettyFormat);
            jedis = pool.getResource();
            jedis.set(key, res);
        } catch (Exception e) {
            logger.error("jedis setJson异常", e);
        } finally {
            release(jedis);
        }
    }

    public static void setJson(Object object) {
        Jedis jedis = null;
        try {
            String res = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss:sss", SerializerFeature.PrettyFormat);
            jedis = pool.getResource();
            jedis.set(getKey(object), res);
        } catch (Exception e) {
            logger.error("jedis setJson异常", e);
        } finally {
            release(jedis);
        }
    }

    public static String getKey(Object obj) {
        try{
            String key = obj.getClass().getName() + "_" + ReflectUtil.get(obj, "id");
            return key;
        }catch (Exception e){
            logger.error("获取缓存key失败", e);
            return null;
        }
    }

    /**
     * 获取保存的json对象，适用于没有implement Serializable的对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getJson(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String res = jedis.get(key);
            return JSON.parseObject(res, clazz);
        } catch (Exception e) {
            logger.error("jedis getJson异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 删除指定的key
     *
     * @param key 需要删除的key，可以使用模糊查询等
     */
    public static void delKeysLike(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Set<String> keys = jedis.keys(key);
            String[] tar = keys.toArray(new String[keys.size()]);
            jedis.del(tar);
        } catch (Exception e) {
            logger.error("jedis getKeyLike 异常", e);
        } finally {
            release(jedis);
        }
    }

    /**
     * 删除指定类型的key
     *
     * @param key
     * @return
     * @throws
     */
    public static Set<String> keysLike(String key) {
        Jedis jedis = null;
        try {
            if (!key.endsWith("*")) {
                key = key + "*";
            }
            jedis = pool.getResource();
            Set<String> keys = jedis.keys(key);
            return keys;
        } catch (Exception e) {
            logger.error("jedis keysLike 异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }


    /**
     * 获取交集的数量
     *
     * @param keys
     * @return
     * @throws
     */
    public static Long sinterForSize(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String tmpKey = "tmp-key" + keys.hashCode() + System.currentTimeMillis();
            Long res = jedis.sinterstore(tmpKey, keys);
            jedis.expire(tmpKey, 10);
            return res;
        } catch (Exception e) {
            logger.error("jedis sinter异常", e);
        } finally {
            release(jedis);
        }
        return null;
    }
}
