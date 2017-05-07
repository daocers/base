package co.bugu.mq.service.impl;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by user on 2017/5/5.
 */
@Service
public class ConsumerConcurrentlyServiceImpl {
    @Autowired
    DefaultMQPushConsumer consumer;

    private Logger logger = LoggerFactory.getLogger(ConsumerOrderlyServiceImpl.class);

    /**
     * 初始化之前执行
     */
    @PostConstruct
    private void init() throws MQClientException {
        consumer.setConsumerGroup(consumer.getConsumerGroup() + "-concurrently");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                logger.info("集群消费，开始接收消息：{}", new Date());
                for (MessageExt message : list) {
                    try {
                        String body = new String(message.getBody(), "utf-8");
                        String topic = message.getTopic();
                        String tag = message.getTags();
                        logger.info("集群消费， 接收到消息：topic: {}, tag: {}, body: {}", new String[]{topic, tag, body});
                    } catch (UnsupportedEncodingException e) {
                        logger.info("集群消费，接收消息失败：", e);
                    }
                }
                logger.info("*******************");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });
        consumer.start();
        logger.debug("集群消费consumer启动完毕。。。");

    }

}
