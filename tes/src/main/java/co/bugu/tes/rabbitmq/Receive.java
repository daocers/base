package co.bugu.tes.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import sun.font.TrueTypeFont;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by daocers on 2016/10/28.
 */
public class Receive {
    private static final String QUEUE_NAME = "chijiu";

    public static  void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        System.out.println(0100);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("[x] waiting for messages. to exit press ctrl+C");
        QueueingConsumer  consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while(true){
            Thread.sleep(1000);
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] received: " + message);
//            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
        }

    }
}
