package co.bugu.rocketmq.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;

/**
 * Created by user on 2017/2/17.
 */
public class Producer {
    public static void main(String[] args) throws IOException {
//        222 223 224 229
//        {"amount":100035.31,"id":194,"type":"repayment"}
        JSONObject json = new JSONObject();
        json.put("result","T" );
        json.put("id", 564);
        json.put("type", "cancelProduct");
        json.put("status", 11);
        String content = json.toJSONString();









                /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer("zgSystemProducer");
        producer.setNamesrvAddr("10.151.37.40:9876;10.151.37.41:9876;10.151.37.42:9876");
//        producer.setNamesrvAddr("10.143.88.76:9876");
//        producer.setNamesrvAddr("192.168.1.128:9876");
        producer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < 3; i++) {
        try {
            /**
             * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
             * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
             * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
             * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
             */
//                JSONObject json = new JSONObject();
//                json.put("id", 217);
//                json.put("invest_rate", 4.6);
//                json.put("status", 3);
//                json.put("factraise_time", 1492151400000L);
//                json.put("type", "productStatus");
            Message msg = new Message("zgsystem",// topic
                    "bill",// tag
                    "send" + System.currentTimeMillis() / 1000,// key
                    content.getBytes());// body
            SendResult sendResult = producer.send(msg);
            System.out.println("发送状态" + sendResult.getSendStatus().name());
            System.out.println("消息id：" + sendResult.getMsgId());
            System.out.println(sendResult);

//            Thread.sleep(1000);
//            JSONObject liubiao = new JSONObject();
//            liubiao.put("id", 217);
//            liubiao.put("status", 5);
//            liubiao.put("type", "productStatus");
//            msg = new Message("zgsystem",// topic
//                    "bill",// tag
//                    "liubiao" + System.currentTimeMillis() / 1000,// key
//                    JSON.toJSONString(liubiao).getBytes());// body
//            sendResult = producer.send(msg);
//            System.out.println("发送状态" + sendResult.getSendStatus().name());
//            System.out.println("消息id：" + sendResult.getMsgId());
//            System.out.println(sendResult);
//
//            JSONObject jiebiao = new JSONObject();
//            jiebiao.put("id", null);

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
//        }
        /**
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出销毁方法里调用shutdown方法
         */
        producer.shutdown();
    }
}
