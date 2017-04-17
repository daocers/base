package co.bugu.rocketmq.my;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.Scanner;

/**
 * Created by daocers on 2017/2/26.
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup");
//        producer.setNamesrvAddr("114.215.142.252:9876");
//                producer.setNamesrvAddr("10.143.108.69:9876;10.143.108.70:9876;10.143.108.71:9876");

        producer.setNamesrvAddr("10.143.88.76:9876");
        producer.setInstanceName("my-producer" + System.currentTimeMillis());
        producer.start();
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("producer start....");
        while(flag){
            String line = scanner.nextLine();
            if(line.equals("by")){
                flag = false;
                continue;
            }
            String topic= "", tag = "", content = "";
            String[] infos = line.split(" ");
            if(infos.length > 0){
                topic = infos[0];
            }
            if(infos.length > 1){
                tag = infos[1];
            }
            if(infos.length > 2){
                content = infos[2];
            }

            Message message = new Message(topic, tag, content.getBytes());
            producer.send(message);
        }
        producer.shutdown();
    }
}
