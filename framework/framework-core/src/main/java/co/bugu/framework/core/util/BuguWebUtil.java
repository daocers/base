package co.bugu.framework.core.util;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by daocers on 2016/8/24.
 * 操作session
 */
public class BuguWebUtil {
    public static void set(HttpServletRequest request, String key, Object value){
        WebUtils.setSessionAttribute(request, key, value);
    }

    public static boolean hasSingin(HttpServletRequest request){
        return WebUtils.getSessionAttribute(request, "userId") != null;
    }

    public static Object get(HttpServletRequest request, String key){
        return WebUtils.getSessionAttribute(request, key);
    }

}
