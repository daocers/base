package co.bugu.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.*;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by user on 2017/2/17.
 */
public class Consumer {
    public static void main(String[] args){
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("consumerGroup");
        consumer.setNamesrvAddr("192.168.58.163.9876");
        try{
//           订阅topic tes下tag 为tag tec的消息
            consumer.subscribe("topic tec", "tag tec");
//            程序第一次启动从消息队列投获取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            //并发消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                    Message message = list.get(0);
                    System.out.println("消息内容为：" + message.toString());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            //顺序消息
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
                    Message message = list.get(0);
                    System.out.println("消息内容为：" + message.toString());
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });

            consumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
