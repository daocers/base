package co.bugu.mq.controller;

import co.bugu.mq.Constant;
import co.bugu.mq.service.impl.ProducerOrderlyServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/5/5.
 */
@Controller
@RequestMapping("/mq")
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    ProducerOrderlyServiceImpl producerOrderlyService;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "good";
    }


    @RequestMapping("/send")
    @ResponseBody
    public String send(Integer count) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for (int i = 0; i < count; i++) {
            String[] userTags = new String[]{"a", "b", "c"};
            String[] paperTags = new String[]{"cat", "dog"};

            Random random = new Random();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);


            // 加个时间后缀
            String body = dateStr + " message " + i;
            String topic = i % 2 == 0 ? "user" : "paper";
            String tags = "";
            if ("user".equals(topic)) {
                tags = userTags[i % 3];
            } else {
                tags = paperTags[i % 3 % 2];
            }
            Message msg = new Message(topic, tags, "KEY" + i, body.getBytes());
            SendResult sendResult = producerOrderlyService.send(topic, tags, body, "key" + System.currentTimeMillis());

            if (sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
//                System.out.println("body:" + body + " " + sendResult);
                Thread.sleep(random.nextInt(2));
            } else {
                logger.error("发送失败： body: {}", body);
                logger.error("发送结果：" + sendResult);
            }
        }
        return new Date().toString();
    }


    @RequestMapping("/getError")
    @ResponseBody
    public String getError() {
        Set<String> consumeSet = Constant.getConsumeSet();
        List<String> sendList = Constant.getSendList();
        Iterator<String> iter = consumeSet.iterator();
        for(String msgId: consumeSet){
            if(sendList.contains(msgId)){
                sendList.remove(msgId);
            }
        }
//        while (iter.hasNext()) {
//            String msgId = iter.next();
//            if (sendList.contains(msgId)) {
//                sendList.remove(msgId);
//                iter.remove();
//            }
//        }
//        JSONObject json = new JSONObject();
//        json.put("sendLeft", sendList);
//        json.put("consumeLeft", consumeSet);
        String res = JSON.toJSONString(sendList, true);
        consumeSet.clear();
        sendList.clear();
        return res;


    }
}
