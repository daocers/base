package co.bugu.rocketmq.demo;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 2017/2/21.
 */
public class ConsumerTest extends Config {

    static {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameServer);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            consumer.subscribe("topic1", "tag1 || tag2");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for(MessageExt messageExt : list){
                    String msg = new String(messageExt.getBody());
                    System.out.println(msg);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
    }


    public static  void main(String[] args){
        Boolean flag = true;
        while(flag){
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if("by".equals(line)){
                flag = false;
                continue;
            }
        }

    }
}
