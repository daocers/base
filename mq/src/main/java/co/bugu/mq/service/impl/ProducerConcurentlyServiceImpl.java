package co.bugu.mq.service.impl;

import co.bugu.mq.service.IProducerService;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by user on 2017/5/5.
 */
@Service
public class ProducerConcurentlyServiceImpl implements IProducerService {
    private Logger logger = LoggerFactory.getLogger(ProducerConcurentlyServiceImpl.class);
    @Autowired
    DefaultMQProducer producer;

    @PostConstruct
    private void init() throws MQClientException {
        producer.start();
        logger.debug("producer concurently started 。。。");
    }

    @Override
    public SendResult send(String topic, String tags, String body, String key) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        SendResult sendResult = producer.send(new Message(topic, tags, key, body.getBytes()));
        logger.info("发送消息：{}， {}， {}， {}", new String[]{topic, tags, body, key});
        logger.info("发送结果： {}", sendResult);
        return sendResult;
    }
}
