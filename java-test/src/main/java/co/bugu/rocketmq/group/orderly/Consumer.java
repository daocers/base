package co.bugu.rocketmq.group.orderly;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.http.impl.ConnSupport;

import java.util.List;

/**
 * Created by user on 2017/5/9.
 */
public class Consumer {
    public static  void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumerGroup(Config.consumerGroup);
        consumer.setInstanceName(Config.consumerInstance);
        consumer.setConsumeMessageBatchMaxSize(10);
        consumer.setNamesrvAddr(Config.namesrvAddr);
        consumer.subscribe(Config.topic, Config.tags);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                consumeOrderlyContext.setAutoCommit(true);
                for(MessageExt message: list){
                    System.out.println(message);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer start...");
    }
}
