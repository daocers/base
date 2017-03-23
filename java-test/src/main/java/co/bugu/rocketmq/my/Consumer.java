package co.bugu.rocketmq.my;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by daocers on 2017/2/26.
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup");
        consumer.setNamesrvAddr("114.215.142.252:9876");
        consumer.setInstanceName("my-consumer" + System.currentTimeMillis());
        consumer.subscribe("his", "qin || han");
        consumer.setVipChannelEnabled(false);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt messageExt: list){
                    String tags = messageExt.getTags();
                    String topic = messageExt.getTopic();
                    String body = new String(messageExt.getBody());
                    System.out.println("当前消息：" + topic + ", " + tags + ", " + body);
                    System.out.println("***");
                }
                System.out.println("-------");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer start...");
    }
}
