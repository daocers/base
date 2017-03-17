package co.bugu.tes.controller;

import co.bugu.framework.core.util.BuguWebUtil;
import co.bugu.framework.core.util.VerifyCodeUtil;
import co.bugu.framework.util.EncryptUtil;
import co.bugu.framework.util.JedisUtil;
import co.bugu.tes.global.Constant;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by daocers on 2017/1/11.
 */
@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IUserService userService;

    /**
     * 跳转到对应的登录页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(ModelMap model, HttpServletRequest request, HttpServletResponse response){
        Cookie[]  cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                String name = cookie.getName();
                if(name.equals("rememberMe")){
                    model.put("rememberMe", 0);
                }
                if("username".equals(name)){
                    model.put("username", cookie.getValue());
                }
            }
        }
        return "index/login";
    }


    @RequestMapping("/index")
    public String index(HttpServletRequest request,  HttpServletResponse response){
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        String rememberMe = session.getAttribute("rememberMe").toString();
        if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(rememberMe)){
            Cookie cookie = new Cookie("username", session.getAttribute("username").toString());
            Cookie remCookie = new Cookie("rememberMe", session.getAttribute("rememberMe").toString());
            Integer max = 3600 * 24 * 7;
            remCookie.setMaxAge(max);
            cookie.setMaxAge(max);
            response.addCookie(cookie);
            response.addCookie(remCookie);
        }
        return "index";
    }
    /**
     * 登陆，传入用户名，密码
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public String signIn(String username, String password, Integer rememberMe, HttpServletRequest request, HttpServletResponse response){
        try{
            User user = new User();
            user.setUsername(username);
            List<User> userList = userService.findByObject(user);
            if(userList.size() == 0){
                return "1";//用户不存在
            }else if(userList.size() > 1){
                return "2";//数据紊乱，应该只有一个匹配，结果有多个
            }else{
                user = userList.get(0);
                String finalPass = EncryptUtil.md5(password + user.getSalt());

                //登陆成功，缓存用户权限，角色信息等
                if(finalPass.equals(user.getPassword())){
                    user = userService.findFullById(user.getId());
                    JedisUtil.setJson(Constant.USER_INFO_PREFIX + user.getId(), user);
                    WebUtils.setSessionAttribute(request, Constant.SESSION_USER_ID, user.getId());
                    if(rememberMe == 0){
                        WebUtils.setSessionAttribute(request, "username", username);
                        WebUtils.setSessionAttribute(request, "rememberMe", rememberMe);
                    }
                    return "0";
                }else{
                    return "3";//密码不正确
                }
            }
        }catch (Exception e){
            logger.error("登录失败", e);
            return "-1";
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/signOut")
    public String signOut(HttpServletRequest request){
        try{
            BuguWebUtil.remove(request, Constant.SESSION_USER_ID);
        }catch (Exception e){
            logger.error("注销失败", e);
        }
        return "redirect:/login.do";
    }


    /**
     * 获取验证码
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/verifyCode")
    public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = VerifyCodeUtil.generateVerifyCode(4);
        request.getSession().setAttribute("verifyCode", code);
        VerifyCodeUtil.outputImage(80, 35, response.getOutputStream(), code);
        return null;
    }

    /**
     * 获取在线信息
     * 有对应的页面信息
     * 管理员，考试教师可用
     * @return
     */
    public String getOnlineInfo(){
        try{

        }catch (Exception e){
            logger.error("获取在线信息失败", e);
        }
        return null;
    }

    public static void main(String[] args){
        String str = "*.findObject";
        str = str.replaceAll("\\*", "\\\\S*");
        System.out.println(str);
    }
}
