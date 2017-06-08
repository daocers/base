package co.bugu.tes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/6/8.
 */
@Controller
@RequestMapping("/help")
public class HelpController {
    private Logger logger = LoggerFactory.getLogger(HelpController.class);

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "帮助中心";
    }
}
