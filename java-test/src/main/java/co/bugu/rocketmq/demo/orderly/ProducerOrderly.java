package co.bugu.rocketmq.demo.orderly;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by daocers on 2017/2/26.
 */
public class ProducerOrderly {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, IOException {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("producer_group_orderly_1");

            producer.setNamesrvAddr("10.143.88.73:9876;10.143.88.74:9876;10.143.88.75:9876");

            producer.start();

            String[] tags = new String[]{"cat", "dog", "pig"};

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);
            for (int i = 0; i < 10; i++) {
                int orderId = i % 2;
                String tag = tags[i % tags.length];
                // 加个时间后缀
                String body = dateStr + " Hello RocketMQ " + orderId + " " + i;
                Message msg = new Message("tes", "dog", "KEY " + tag, body.getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                        Integer index = (Integer) arg;
                        return list.get(index % list.size());
                    }
                }, orderId);
                System.out.println("发送结果：" + ", body:" + body + sendResult);
            }

            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.in.read();
    }
}
