package co.bugu.tes.interceptor;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.Authority;
import co.bugu.tes.model.Role;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IAuthorityService;
import co.bugu.tes.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (HandlerMethod.class.equals(o.getClass())) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            Object controller = handlerMethod.getBean();
            Method method = handlerMethod.getMethod();

            List<Annotation> annotations = getAllWorkAnnotation(controller, method);
            boolean checkRes = true;
            User user = userService.findFullById(Integer.valueOf((String) BuguWebUtil.get(request, Constant.SESSION_USER_ID)));
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(requiresLogin.class)) {
                    checkRes = BuguWebUtil.hasSingin(request);
                } else {
                    checkRes = checkAuth(user, annotation);
                }
                if (!checkRes) {
                    break;
                }
            }
            if (!checkRes) {//如果检验没通过，直接跳转到未授权界面，提示信息
                response.sendRedirect("/unAuth.jsp");
                return false;
            }

        }
        return true;
    }


    /**
     * 检查权限
     *
     * @param annotation
     * @return
     */
    private boolean checkAuth(User user, Annotation annotation) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = annotation.annotationType();
        Method val = clazz.getDeclaredMethod("value");
        val.setAccessible(true);
        Object param = val.invoke(annotation, null);
        if (requiresAllRole.class.equals(annotation.annotationType())) {
            String[] roles = (String[]) param;
            List<String> roleList = Arrays.asList(roles);
            List<Role> hasRoles = user.getRoleList();
            for (Role role : hasRoles) {
                if (!roleList.contains(role.getCode())) {
                    return false;
                }
            }
            return true;
        } else if (requiresAnyRole.class.equals(annotation.annotationType())) {
            String[] roles = (String[]) param;
            List<String> roleList = Arrays.asList(roles);
            List<Role> hasRoles = user.getRoleList();
            for (Role role : hasRoles) {
                if (roleList.contains(role.getCode())) {
                    return true;
                }
            }
            return false;
        } else if (requiresRole.class.equals(annotation.annotationType())) {
            String roleStr = (String) param;
            List<Role> hasRoles = user.getRoleList();
            for (Role role : hasRoles) {
                if (role.getCode().equals(roleStr)) {
                    return true;
                }
            }
            return false;

        } else if (requiresAllPermission.class.equals(annotation.annotationType())) {
            String[] perms = (String[]) param;
            List<String> permissionList = Arrays.asList(perms);
            List<Authority> authorities = user.getAuthorityList();
            for (Authority authority : authorities) {
                if (!permissionList.contains(authority.getUrl())) {
                    return false;
                }
            }
            return true;

        } else if (requiresAnyPermission.class.equals(annotation.annotationType())) {
            String[] perms = (String[]) param;
            List<String> permissionList = Arrays.asList(perms);
            List<Authority> authorities = user.getAuthorityList();
            for (Authority authority : authorities) {
                if (permissionList.contains(authority.getUrl())) {
                    return true;
                }
            }
            return false;
        } else if (requiresPermission.class.equals(annotation.annotationType())) {
            String perm = (String) param;
            List<Authority> authorities = user.getAuthorityList();
            for (Authority authority : authorities) {
                if (perm.equals(authority.getUrl())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    /**
     * 获取所有有效的注解
     *
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
