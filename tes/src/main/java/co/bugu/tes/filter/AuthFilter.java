package co.bugu.tes.filter;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.tes.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by daocers on 2017/3/11.
 */
public class AuthFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    IUserService userService;
    @Autowired
    IAuthorityService authorityService;
    private static List<String> excludeUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclude = filterConfig.getInitParameter("exclude");
        if(StringUtils.isNotEmpty(exclude)){
            String[] arr = exclude.split(",");
            for(String info: arr){
                if(info.contains("*")){
                    info = info.replaceAll("\\*", "\\\\S*");
                }
                excludeUrls.add(info);
            }
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        BuguWebUtil.set(request, "previous", url);
        if(!BuguWebUtil.hasSingin(request)){//未登录
            String method = request.getMethod();
            Map<String, String[]> params = request.getParameterMap();
            boolean needFilter = true;
            for(String exclude : excludeUrls){
                boolean mathch = Pattern.matches(exclude, url);
                if(mathch){
                    needFilter = false;
                    break;
                }
            }
            if(needFilter){//需要过滤
                if(request.getHeader("X-Requested-With") == null){//常规请求
                    response.sendRedirect("/login.do");
                }else{//ajax请求
                    ServletOutputStream os = response.getOutputStream();
                    os.print("login");
                    os.flush();
                    os.close();
                }
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
