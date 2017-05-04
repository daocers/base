package co.bugu.data.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by user on 2017/4/15.
 */
public class MQOrderlyConsumer {
    private Logger logger = LoggerFactory.getLogger(MQOrderlyConsumer.class);

    DefaultMQPushConsumer consumer = null;

    private String consumerGroup;
    private String nameServerAddr;
    private String instanceName;
    private String topic;
    private String tags;

    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameServerAddr);
        consumer.setInstanceName("my-consumer" + System.currentTimeMillis());
        consumer.subscribe(topic, tags);
        consumer.setVipChannelEnabled(false);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    logger.info("收到消息，消息体：{}", new String(msg.getBody()));
                    logger.info("消息内容 : " + msg.toString());
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        logger.info("mq consumer启动成功");
    }

    public void destory() {
        logger.info("销毁mqconsumer");
        consumer.shutdown();
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getNameServerAddr() {
        return nameServerAddr;
    }

    public void setNameServerAddr(String nameServerAddr) {
        this.nameServerAddr = nameServerAddr;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}
