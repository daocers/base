package co.bugu.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/4/13.
 */
@RequestMapping("/mq")
@Controller
public class MQSendController {
    private Logger logger = LoggerFactory.getLogger(MQSendController.class);

    @RequestMapping("/index")
    public String toSend() {
        return "mq/send";
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(String nameServerAddr, String topic, String tags, String body) {
        return null;
    }


}
