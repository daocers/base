package co.bugu.rocketmq.demo.orderly;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;


/**
 * 顺序消息消费，带事务方式（应用可控制Offset什么时候提交）
 */
public class ConsumerOrderly {

    public static void main(String[] args) throws MQClientException {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_3");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("common-group");
//        consumer.setNamesrvAddr("10.143.88.73:9876;10.143.88.74:9876;10.143.88.75:9876");
        consumer.setNamesrvAddr("127.0.0.1:9876");
//        consumer.setNamesrvAddr("192.168.1.128:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("aaaaa", "TagA1 || TagC1 || TagD1");
//        consumer.setClientIP("192.168.1.3");
        consumer.setPullBatchSize(10);
        consumer.registerMessageListener(new MessageListenerOrderly() {

            Random random = new Random();

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: ");
                for (MessageExt msg : msgs) {
                    System.out.println( "content:" + new String(msg.getBody()) + "; " + msg);
                }
//                try {
//                    //模拟业务逻辑处理中...
//                    TimeUnit.SECONDS.sleep(random.nextInt(10));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.println("Consumer Started.");
    }

}