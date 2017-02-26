package co.bugu.rocketmq.demo;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 2017/2/21.
 */
public class ProducerTest extends Config {
    private static DefaultMQProducer producer;

    static {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServer);
        producer.setClientIP("192.168.0.3");
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            System.out.print("请输入 msg topic tag：");

            String line = scanner.nextLine();
            if ("by".equals(line)) {
                flag = false;
                producer.shutdown();
                continue;
            }
            String[] info = line.split(" ");
            String msg = info[0];
            String topic = null;
            String tag = null;
            if (info.length > 1) {
                topic = info[1];
            }
            if (info.length > 2) {
                tag = info[2];
            }
            Message message = new Message(topic, tag, msg.getBytes());
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    return list.get(((Integer)o) % list.size());
                }
            }, 0);
            System.out.println(result);
        }
    }
}
