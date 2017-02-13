package co.bugu.framework.core.mybatis;

import co.bugu.framework.core.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by user on 2017/2/13.
 * 搜索参数帮助类
 */
public class SearchParamUtil {
    private static Logger logger = LoggerFactory.getLogger(SearchParamUtil.class);

    /**
     * 根据获取到的查询参数为查询实体类赋值
     * 查询参数格式为： LK_name, IN_age之类
     * @param obj
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getParamObject(T obj, Map<String, Object> param) throws Exception {
        if(obj == null){
            throw new Exception("参数类型不能为空");
        }
        Iterator<Map.Entry<String, Object>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object val = entry.getValue();
            if(key.contains("_")){
                String fieldName = key.split("_")[1];
                ReflectUtil.setValue(obj, fieldName, val);
            }else{
                logger.error("查询参数不合法，参数：{}", key);
                throw new Exception("不合法的查询参数");
            }
        }
        return obj;
    }

    /**
     * 直接从HttpServletRequest对象中获取查询参数
     * @param obj
     * @param request
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getParamObject(T obj, HttpServletRequest request) throws Exception {
        if(obj == null){
            throw new Exception("参数类型不能为空");
        }
        Map<String, Object> param = request.getParameterMap();
        Iterator<Map.Entry<String, Object>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object val = entry.getValue();
            if(key.contains("_")){
                String fieldName = key.split("_")[1];
                ReflectUtil.setValue(obj, fieldName, val);
            }
        }
        return obj;
    }
}
