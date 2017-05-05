package co.bugu.rocketmq.demo.common;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.Random;

/**
 * Created by user on 2017/5/5.
 */
public class ProducerCommon {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        Integer successCount = 0;
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("producer-group-1");
        producer.setInstanceName("producer-ins-1");
//        producer.setClientIP("127.0.0.1");
        producer.setVipChannelEnabled(false);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setCreateTopicKey("paper");

        producer.start();
        Random random = new Random();

        for(int i = 0 ; i < 1; i++){
            Thread.sleep(random.nextInt(10));
            String[] tags = new String[]{"dog", "cat"};
            SendResult result = producer.send(new Message("paper", tags[i % 2], "key" + i, ("message body " + i).getBytes()));
            System.out.println(result);
            if(result.getSendStatus().equals(SendStatus.SEND_OK)){
                successCount++;
            }
        }
        System.out.println("发送成功：" + successCount + "条");
        producer.shutdown();
    }
}
