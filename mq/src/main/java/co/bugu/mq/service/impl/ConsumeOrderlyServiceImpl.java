package co.bugu.mq.service.impl;

import ch.qos.logback.core.ConsoleAppender;
import co.bugu.framework.util.DateUtil;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2017/5/5.
 */
@Service
public class ConsumeOrderlyServiceImpl {
    volatile Set<String> received =new HashSet<>();
    @Autowired
    DefaultMQPushConsumer consumer;

    private Logger logger = LoggerFactory.getLogger(ConsumeOrderlyServiceImpl.class);

    /**
     * 初始化之前执行
     */
    @PostConstruct
    private void init() throws MQClientException {
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                logger.info("顺序消费，开始接收消息：{}", new Date());
                for(MessageExt message: list){
                    try {
                        String msgId = message.getMsgId();
                        received.add(msgId);
                        String body = new String(message.getBody(), "utf-8");
                        String topic = message.getTopic();
                        String tag = message.getTags();
                        logger.info("顺序消费，接收到消息：topic: {}, tag: {}, body: {}", new String[]{topic, tag, body});
                    } catch (UnsupportedEncodingException e) {
                        logger.info("顺序消费，接收消息失败：", e);
                    }
                }
                logger.info("*******************");
                logger.info("已处理消息：" + received.size() + "条");
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        logger.debug("顺序消费consumer启动完毕。。。");

    }


}
