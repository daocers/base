package co.bugu.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by user on 2017/4/12.
 */
public class MQProducerConsumerService {
    private Logger logger = LoggerFactory.getLogger(MQProducerConsumerService.class);
    private DefaultMQProducer producer;
    private String nameServerAddr;
    private String topic;
    private String tag;
    private String producerName;
    private String instanceName;

    private Random random = new Random();

    public void init() throws MQClientException {
        producer = new DefaultMQProducer(producerName);
        producer.setNamesrvAddr(nameServerAddr);
        producer.setVipChannelEnabled(false);
        producer.setInstanceName(System.currentTimeMillis() + "");
        producer.start();
        logger.info("mq 成功启动。。。");

    }


    public void destory(){
        producer.shutdown();
        logger.info("mq 成功关闭。。。。");
    }


    public String sendMsg(String body) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if(StringUtils.isEmpty(body)){
            logger.debug("消息体为空，忽略");
            return null;
        }
        Message message = new Message(topic, tag,
                Thread.currentThread().getName() + System.currentTimeMillis() + random.nextInt(1000),
                body.getBytes("utf-8"));

        logger.info("消息：{}", JSON.toJSONString(message));
        SendResult sendResult = producer.send(message);
        if(sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            logger.debug("发送成功, msgId: {}", sendResult.getMsgId());
            return sendResult.getMsgId();
        }else{
            logger.error("发送失败");
            return "";
        }
    }


    public String getNameServerAddr() {
        return nameServerAddr;
    }

    public void setNameServerAddr(String nameServerAddr) {
        this.nameServerAddr = nameServerAddr;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
