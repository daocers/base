package co.bugu.rocketmq.demo.orderly;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Producer，发送顺序消息
 */
public class ProducerOrderly {
    public static void main(String[] args) throws IOException, IOException {
        try {
            Set<String> count = new HashSet<>();
            DefaultMQProducer producer = new DefaultMQProducer("producer-group-1");

//            producer.setNamesrvAddr("10.143.88.73:9876;10.143.88.74:9876;10.143.88.75:9876");
//            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.setNamesrvAddr("192.168.1.128:9876");
            producer.start();

            String[] userTags = new String[]{"a", "b", "c"};
            String[] paperTags = new String[]{"cat", "dog"};

            Random random = new Random();
            for (int i = 0; i < 100000; i++) {

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = sdf.format(date);


                // 加个时间后缀
                String body = dateStr + " message " + i;
                String topic = i % 2 == 0 ? "user" : "paper";
                String tags = "";
                if ("user".equals(topic)) {
                    tags = userTags[i % 3];
                }else{
                    tags = paperTags[i % 3 % 2];
                }
                Message msg = new Message(topic, tags, "KEY" + i, body.getBytes());

//                System.out.println(topic + " " + tags);
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                        Integer id = Math.abs(arg.hashCode());
//                        System.out.println(id);
                        return mqs.get(id % mqs.size());
                    }
                }, body);//0是队列的下标

                count.add(sendResult.getMsgId());
                if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
                    System.out.println("发送失败； " + sendResult);
                }
//                System.out.println("body:" + body + " " + sendResult);
                Thread.sleep(random.nextInt(2));
            }

            System.out.println("发送成功： " + count.size());
            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.in.read();
    }
}