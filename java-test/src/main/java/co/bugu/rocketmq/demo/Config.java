package co.bugu.rocketmq.demo;

/**
 * Created by user on 2017/2/21.
 */
public class Config {
    protected static String nameServer = "192.168.1.128:9876";
//    protected static String nameServer = "10.143.88.58:9876;10.143.88.59:9876;10.143.88.60:9876";
    protected static String consumerGroup = "consumer" + System.currentTimeMillis();
    protected static String producerGroup = "producer" + System.currentTimeMillis();


}
