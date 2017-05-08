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
        String producerGroup = "common-group";
        String instance = "producer-ins-1";
        String namesrvAddr = "0.152.4.83:9876;10.152.4.86:9876";
        String key = "key1";
        String body = "i am the test message.";
        String topic = "user";
        String tags = "a";

        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(producerGroup);
        producer.setInstanceName(instance);
        producer.setVipChannelEnabled(false);
        producer.setNamesrvAddr(namesrvAddr);

        producer.start();

        for(int i = 0; i < 10; i++){
            SendResult result = producer.send(new Message(topic, tags, key, body.getBytes()));
            if(!result.getSendStatus().equals(SendStatus.SEND_OK)){
                System.out.println("发送失败：" + result);

            }

        }

        producer.shutdown();
    }
}
