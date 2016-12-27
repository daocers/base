package co.bugu.sso.interceptor;

import co.bugu.sso.util.TicketUtil;
import co.bugu.sso.constant.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by daocers on 2016/6/28.
 */
public class AuthInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.debug("****开始拦截****");
        String url = request.getRequestURL().toString();
        String authUrl = GlobalConstant.getAuthServer() + "?url=" + url;
        Cookie[] cookies = request.getCookies();
        logger.debug("没有cookie");
        if(cookies != null){
            for(Cookie cookie: cookies){
                if("ticket".equals(cookie.getName())){
                    logger.debug("cookie带有票据信息, domain:{}, ticket:{}",
                            new String[]{cookie.getDomain(), cookie.getValue()});
                    boolean isValid = TicketUtil.checkTicket(cookie.getValue());
                    if(isValid){
                        logger.debug("票据校验通过");
                        return true;
                    }else{
                        logger.debug("票据校验未通过");
                        response.sendRedirect(authUrl);
                        return false;
                    }
                }
            }
        }

        String ticket = request.getParameter("ticket");
        if(ticket == null){
            logger.debug("没有票据信息");
            response.sendRedirect(authUrl);
            return false;
        }else{
            logger.debug("带有票据信息");
            boolean isValid = TicketUtil.checkTicket(ticket);
            if(isValid){
                logger.debug("票据校验通过");
                Cookie cookie = new Cookie("ticket", ticket);
                cookie.setDomain(request.getServerName());
                cookie.setPath("/");
                response.addCookie(cookie);
                response.sendRedirect(url);
                return false;
            }else{
                logger.debug("票据校验未通过，转向验证中心");
                response.sendRedirect(authUrl);
                return false;
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }



}
