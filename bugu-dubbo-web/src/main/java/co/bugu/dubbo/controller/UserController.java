package co.bugu.dubbo.controller;

import co.bugu.dubbo.service.IUserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daocers on 2016/10/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return userService.getName();
    }

}
