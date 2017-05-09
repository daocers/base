package co.bugu.rocketmq.group.orderly;

/**
 * Created by user on 2017/5/9.
 */
public interface Config {
    String namesrvAddr = "10.152.4.83:9876;10.152.4.86:9876";
    String topic = "mylc_test";
    String tags = "*";
    String consumerGroup = "consumer-group";
    String producerGroup = "producer-group";
    String consumerInstance = "consumer-instance";
    String producerInstance = "producer-instance";
}
