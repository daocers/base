package co.bugu.tes.interceptor;

import co.bugu.framework.core.util.BuguWebUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by daocers on 2016/8/25.
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //先判断是否已经登录
        if(!BuguWebUtil.hasSingin(request)){
            request.getRequestDispatcher("/index/login.do").forward(request, response);
            return false;
        }

        //判断是否有权限
        String url = request.getRequestURI();
        String method = request.getMethod();
        String fd = request.getQueryString();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
