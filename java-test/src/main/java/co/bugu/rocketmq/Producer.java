package co.bugu.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by user on 2017/2/17.
 */
public class Producer {
    public static void main(String[] args){
        DefaultMQProducer producer = new DefaultMQProducer("producer group 1");
        producer.setNamesrvAddr("192.168.58.163:9876");
        try{
            producer.start();
            Message message = new Message("topic tec",
                    "tag tec",
                    "key-orderId,一般使用数字型",
                    "Just for fun.".getBytes());
            SendResult result = producer.send(message);
            System.out.print("id:" + result.getMsgId() + " result: " + result.getSendStatus());
            message = new Message("topic tec",
                    "tag tec",
                    "2",
                    "message 2".getBytes());
            result = producer.send(message);
            System.out.print("id:" + result.getMsgId() + " result: " + result.getSendStatus());

            message = new Message("topic tec",
                    "tag tec",
                    "3",
                    "message 3".getBytes());
            result = producer.send(message);
            System.out.print("id:" + result.getMsgId() + " result: " + result.getSendStatus());

            message = new Message("topic tec",
                    "tag tec",
                    "4",
                    "message 4".getBytes());
            result = producer.send(message);
            System.out.print("id:" + result.getMsgId() + " result: " + result.getSendStatus());

            message = new Message("topic tec",
                    "tag tec",
                    "5",
                    "message 5".getBytes());
            result = producer.send(message);
            System.out.print("id:" + result.getMsgId() + " result: " + result.getSendStatus());

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }
}
