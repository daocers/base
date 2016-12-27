package co.bugu.tes.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by daocers on 2016/10/28.
 */
public class Send {
    private static final String QUEUE_NAME = "chijiu";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDelete("chijiu");
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

//        for(int i = 0; i < 10000; i++){
//            String message = "hello world: " + i;
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//            System.out.println("[x] sent : " + message);
//        }

        int i = 0;
        while(true){
            Thread.sleep(500);
            i++;

            String message = "hello message: " + i;

            channel.confirmSelect();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println("[X] SENT: " + message);
        }

//        channel.close();
//        connection.close();
    }
}
