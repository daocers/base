package co.bugu.tes.init;

import co.bugu.framework.core.util.ApplicationContextUtil;
import co.bugu.tes.global.MqConstant;
import co.bugu.tes.model.Parent;
import co.bugu.tes.service.IParentService;
import co.bugu.tes.service.impl.ParentServiceImpl;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by daocers on 2016/10/30.
 */
public class MqConsumerListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(MqConsumerListener.class);

    private Connection connection = null;

    private Channel channel = null;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug("上下文初始化完毕");
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(MqConstant.QUEUE_NAME, true, false, false, null);
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(MqConstant.QUEUE_NAME, false, consumer);

            IParentService parentService = ApplicationContextUtil.getBean(ParentServiceImpl.class);
            new Thread(){
                @Override
                public void run(){
                    while (true){

                        QueueingConsumer.Delivery delivery = null;

                        try {

                            delivery = consumer.nextDelivery();
                            String message = new String(delivery.getBody(), "UTF-8");
                            logger.debug("收到消息：{}", message);

                            Random random = new Random();
                            Parent parent = new Parent();
                            parent.setAge(random.nextInt(30) + 1);
                            parent.setName("test" + random.nextInt(1000));
                            parent.setProp("propinfo" + random.nextBoolean() + random.nextInt(10));

                            parentService.save(parent);

                            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
                        }catch (Exception e){
                            try {
                                if(channel != null){
                                    channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }
                }
            }.start();
            logger.debug("done");



        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("销毁");
        try{
            if(channel != null){
                channel.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            logger.error("上下文销毁失败", e);
        }

    }
}
