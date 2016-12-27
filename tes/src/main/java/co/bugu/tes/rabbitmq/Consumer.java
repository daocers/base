package co.bugu.tes.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by daocers on 2016/10/28.
 */
public class Consumer {
    private final static String QUEUE_NAME = "rabbitmq.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, true, null);
        System.out.println("Consumer waiting received messages");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws UnsupportedEncodingException {
                String message = new String(body, "UTF-8");
                System.out.println("Consumer received : " + message);
            }
        };
//        自动回复队列应答  rabbitmq消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
