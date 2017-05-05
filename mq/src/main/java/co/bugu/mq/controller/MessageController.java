package co.bugu.mq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/5/5.
 */
@Controller
@RequestMapping("/mq")
public class MessageController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "good";
    }
}
