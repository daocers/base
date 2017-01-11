package co.bugu.tes.controller;

import co.bugu.framework.core.util.VerifyCodeUtil;
import co.bugu.framework.util.exception.TesException;
import co.bugu.tes.model.User;
import co.bugu.tes.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by daocers on 2017/1/11.
 */
@RequestMapping("/login")
@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userService;

    /**
     * 跳转到对应的登录页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }


    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @return
     * @throws TesException
     */
    @RequestMapping("/signIn")
    public String singIn(String username, String password, HttpServletRequest request) throws TesException {
        try{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            List<User> list = userService.findByObject(user);
            if(list.size() == 0){
                logger.warn("未找到用户");
                return "1";
            }
        }catch (Exception e){
            logger.error("登录失败， 用户： {}", username, e);
            throw new TesException("登录失败", e);
        }
        return null;

    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/signOut")
    public String signOut(HttpServletRequest request){
        try{
            request.getSession().removeAttribute("username");
        }catch (Exception e){

        }
        return null;
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
}
