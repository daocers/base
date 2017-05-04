package co.bugu.rocketmq.common;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by user on 2017/2/17.
 */
public class Consumer {
    /**
     * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
     * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
     */
    public static void main(String[] args) {

        /**
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("consumerGroupName");
        pushConsumer.setNamesrvAddr("10.143.88.73:9876;10.143.88.74:9876;10.143.88.75:9876");
//        pushConsumer.setNamesrvAddr("192.168.1.128:9876");
        pushConsumer.setInstanceName("Consumer");
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        pushConsumer.setVipChannelEnabled(false);
        try {
            /**
             * 订阅指定topic下tags分别等于TagA或TagC或TagD
             * 两个参数：第一个参数是topic第二个参数是tags
             */
            pushConsumer.subscribe("mylc", "pushParty || bill || TagD");
            /**
             * 订阅指定topic下所有消息<br>
             * 注意：一个consumer对象可以订阅多个topic
             */
            //pushConsumer.subscribe("TopicTest2", "*");
            pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                    System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
                    MessageExt messageExt = msgs.get(0);
                    if("mylc".equals(messageExt.getTopic())){
                        // 执行TopicTest1的消费逻辑
                        if (messageExt.getTags() != null && messageExt.getTags().equals("TagA")) {
                            // 执行TagA的消费
                            System.out.println(new String(messageExt.getBody()));
                        }else if(messageExt.getTags() != null && messageExt.getTags().equals("TagB")){
                            System.out.println(new String(messageExt.getBody()));
                        }else if(messageExt.getTags() != null && messageExt.getTags().equals("TagC")) {
                            System.out.println(new String(messageExt.getBody()));
                        }
                    }else if("TopicTest2".equals(messageExt.getTopic())){
                        System.out.println(new String(messageExt.getBody()));
                    }
                    System.out.println("接收到消息：id为 " + messageExt.getMsgId());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        try {
            pushConsumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.println("Consumer Started.");
    }
}
