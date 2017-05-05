package co.bugu.rocketmq.demo.common;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 2017/5/5.
 */
public class ConsumerCommon {
    public static void main(String[] args) throws MQClientException {
//        System.out.println("enter your subscribe tags: ");
//        Scanner scanner = new Scanner(System.in);
//        String tags = scanner.nextLine().trim();

        final TempCount consumerCount =new TempCount();

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup("consumer-group-1");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("consumer-ins-1");
        consumer.setConsumeMessageBatchMaxSize(10);
//        consumer.setClientIP("127.0.0.1");
        consumer.setVipChannelEnabled(false);
        consumer.subscribe("user", "a || b");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("收到消息：");
                for(MessageExt msg: list){
                    String info = new String(msg.getBody());
                    System.out.println("收到消息：" + info + " " +  msg);
                    consumerCount.count++;
                }
                System.out.println("当前消费消息：" + consumerCount.count + "条");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer启动完毕。。。。");

    }

}

class TempCount{
    Integer count = 0;
}

