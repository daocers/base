package co.bugu.tes.init;

import co.bugu.framework.core.util.ApplicationContextUtil;
import co.bugu.tes.global.MqConstant;
import co.bugu.tes.model.Parent;
import co.bugu.tes.service.IParentService;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by daocers on 2016/10/29.
 */
public class OrderMqConsumer implements ServletContextListener {
    private IParentService parentService = null;
    private static Logger logger = LoggerFactory.getLogger(OrderMqConsumer.class);

    private static Connection connection = null;
    private static Channel channel = null;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
//        this.initWebApplicationContext(event.getServletContext());

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
        try {
//            Connection connection = factory.newConnection();
//            Channel channel = connection.createChannel();
            channel.queueDelete(MqConstant.QUEUE_NAME);
            channel.queueDeclare(MqConstant.QUEUE_NAME, true, false, false, null);

            parentService = ApplicationContextUtil.getBean(IParentService.class);

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException {
                    try{
                        String message = new String(body, "UTF-8");
                        System.out.println("收到消息，" + message);

                        Random random = new Random();
                        Parent parent = new Parent();
                        parent.setAge(random.nextInt(30) + 1);
                        parent.setName("test" + random.nextInt(1000));
                        parent.setProp("propinfo" + random.nextBoolean() + random.nextInt(10));

                        parentService.save(parent);

                        channel.basicAck(envelope.getDeliveryTag(), true);
                    }catch (Exception e){
                        logger.error("处理消息失败", e);
                        channel.basicNack(envelope.getDeliveryTag(), false, true);
                    }


                }
            };
            while (true) {
                channel.basicConsume(MqConstant.QUEUE_NAME, false, consumer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
