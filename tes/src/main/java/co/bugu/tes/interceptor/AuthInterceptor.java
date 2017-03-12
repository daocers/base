package co.bugu.tes.interceptor;

import co.bugu.tes.service.IAuthorityService;
import co.bugu.tes.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daocers on 2016/8/25.
 */
public class    AuthInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Autowired
    IUserService userService;
    @Autowired
    IAuthorityService authorityService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(HandlerMethod.class.equals(o.getClass())){
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Object controller = handlerMethod.getBean();
            Method method = handlerMethod.getMethod();

            List<Annotation> annotations = getAllWorkAnnotation(controller, method);
            boolean checkRes = true;
            for(Annotation annotation: annotations){
                boolean pass = checkAuth(annotation);
                if(!pass){
                    checkRes = false;
                    break;
                }
            }
            if(!checkRes){//如果检验没通过，直接跳转到未授权界面，提示信息
                response.sendRedirect("/unAuth.jsp");
                return false;
            }

        }
        return true;
    }


    /**
     * 检查权限
     * @param annotation
     * @return
     */
    private boolean checkAuth(Annotation annotation) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = annotation.annotationType();
        Method val = clazz.getDeclaredMethod("value");
        val.setAccessible(true);
        String result = (String) val.invoke(annotation, null);
        if(requiredAllRole.class.equals(annotation.annotationType())){

        }else if(requiredAnyRole.class.equals(annotation.annotationType())){

        }else if(requiredRole.class.equals(annotation.annotationType())){

        }else if(requiredAllPermission.class.equals(annotation.annotationType())){

        }else if(requiredAnyPermission.class.equals(annotation.annotationType())){

        }else if(requiredPermission.class.equals(annotation.annotationType())){

        }else if(requiredLogin.class.equals(annotation.annotationType())){

        }
        return true;
    }


    /**
     * 获取所有有效的注解
     * @param controller
     * @param method
     * @return
     */
    private List<Annotation> getAllWorkAnnotation(Object controller, Method method) {
        Annotation[] controllerAnnos = controller.getClass().getDeclaredAnnotations();
        Annotation[] methodAnnos = method.getDeclaredAnnotations();

        List<Annotation> worked = new ArrayList<>();
        worked.addAll(Arrays.asList(controllerAnnos));
        worked.addAll(Arrays.asList(methodAnnos));
        return worked;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
