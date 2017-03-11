package co.bugu.tes.interceptor;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.tes.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by daocers on 2016/8/25.
 */
public class AuthInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Autowired
    IUserService userService;
    @Autowired
    IAuthorityService authorityService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //先判断是否已经登录
        if(!BuguWebUtil.hasSingin(request)){
//            request.getRequestDispatcher("/login.do").forward(request, response);
            response.sendRedirect("/login.do");
            return false;
        }

        //判断是否有权限 暂时不做处理
//        String url = request.getRequestURI();
//        String method = request.getMethod();
//        String fd = request.getQueryString();
//
//        User user = userService.findById((Integer) BuguWebUtil.get(request, Constant.SESSION_USER_ID));
//        Authority authority = new Authority();
//        authority.setUrl(url);
//        authority.setAcceptMethod(method.toUpperCase());
//        List<Authority> authorityList = authorityService.findByObject(authority);
//        if(authorityList == null || authorityList.size() == 0){
//            logger.error("权限体系没有数据");
//            return false;
//        }
//        if(authorityList != null && authorityList.size() > 0){
//            authority = authorityList.get(0);
//            logger.error("权限体系异常 url: {} 在系统中存在多个", url);
//            return false;
//        }
//        if(!user.getAuthorityList().contains(authority)){
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
