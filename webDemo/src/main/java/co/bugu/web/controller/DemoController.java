package co.bugu.web.controller;

import co.bugu.framework.core.util.ApplicationContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by daocers on 2016/6/29.
 */
@RequestMapping(value = "/demo")
@Controller
public class DemoController {

    @RequestMapping(value = "/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        RequestMappingHandlerMapping requestMappingHandlerMapping =
                ApplicationContextUtil.getBean(RequestMappingHandlerMapping.class);
        return  "";
    }
}


