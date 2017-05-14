package co.bugu.rocketmq.group.orderly;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * Created by user on 2017/5/9.
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(Config.producerGroup);
        producer.setNamesrvAddr(Config.namesrvAddr);
        producer.setInstanceName(Config.producerInstance);
        producer.start();
//        JSONObject json = new JSONObject();
//        json.put("type", "cancelProduct");
//        json.put("result", "T");
//        json.put("id", "27143");

        JSONObject json = new JSONObject();
//        json.put("status", "11");
//        json.put("id", "27143");
//        json.put("type", "productStatus");
        json.put("id", 27010);
        json.put("type", "returnTicket");

        Long now = System.currentTimeMillis();
        SendResult sendResult = producer.send(new Message(Config.topic, "bill", now + "",
                json.toJSONString().getBytes()), new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {

                return list.get(Integer.valueOf((Integer) o)%list.size());
            }
        }, json.getInteger("id"));
        System.out.println("发送结果：" + sendResult);
        producer.shutdown();
    }
}
