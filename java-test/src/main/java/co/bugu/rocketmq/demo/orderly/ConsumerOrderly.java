package co.bugu.rocketmq.demo.orderly;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by daocers on 2017/2/26.
 */
public class ConsumerOrderly {
    public static void main(String[] args) throws MQClientException, IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group_orderly_1");
        consumer.setNamesrvAddr("10.143.88.73:9876;10.143.88.74:9876;10.143.88.75:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.setPullBatchSize(10);

        consumer.subscribe("zoo", "dog || cat");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                this.consumeTimes.incrementAndGet();

                for (MessageExt msg : msgs) {
                    System.out.println("收到消息：content: 【" + new String(msg.getBody()) + "】 " + msg);
                }

//                if ((this.consumeTimes.get() % 2) == 0) {
//                    return ConsumeOrderlyStatus.SUCCESS;
//                } else if ((this.consumeTimes.get() % 3) == 0) {
//                    return ConsumeOrderlyStatus.ROLLBACK;
//                } else if ((this.consumeTimes.get() % 4) == 0) {
//                    return ConsumeOrderlyStatus.COMMIT;
//                } else if ((this.consumeTimes.get() % 5) == 0) {
//                    context.setSuspendCurrentQueueTimeMillis(3000);
//                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//                }

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
        System.in.read();
    }
}
