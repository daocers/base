package co.bugu.data.controller;

import co.bugu.data.mq.MQOrderlyProducer;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Created by user on 2017/5/4.
 */
@RequestMapping("/mq")
@Controller
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MQOrderlyProducer producer;
    @ResponseBody
    @RequestMapping("/send")
    public String send() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        producer.sendMsg("good", "dog", 1);
        return "good";
    }

    @ResponseBody
    @RequestMapping("/testNull")
    public String test(){
        String name = null;
        name.length();
        return "good";
    }

}
