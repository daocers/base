package co.bugu.mq.service.impl;

import co.bugu.mq.service.IProducerService;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by user on 2017/5/5.
 */
//@Service
public class ProducerOrderlyServiceImpl implements IProducerService {
    //    private Logger logger = LoggerFactory.getLogger(ProducerOrderlyServiceImpl.class);
    private Logger logger = LoggerFactory.getLogger("send");
    @Autowired
    DefaultMQProducer producer;

    @PostConstruct
    public void init() throws MQClientException {
        producer.setProducerGroup(producer.getProducerGroup() + "-orderly");
        producer.start();
    }

    @Override
    public SendResult send(String topic, String tags, String body, String key) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String name = producer.getInstanceName();
        SendResult result = producer.send(new Message(topic, tags, key, body.getBytes()), new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                Integer hashCode = Math.abs(o.hashCode());
                return list.get(hashCode % list.size());
            }
        }, key);

//        logger.info("发送消息：{}， {}， {}， {}", new String[]{topic, tags, body, key});
        logger.info("发送结果：" + name + " {}", result);
        return result;


    }
}
