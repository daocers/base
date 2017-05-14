package co.bugu.rocketmq.group.orderly;

/**
 * Created by user on 2017/5/9.
 */
public interface Config {
//    String namesrvAddr = "10.151.37.40:9876;10.151.37.41:9876;10.151.37.42:9876";
    String namesrvAddr = "10.143.108.83:9876;10.143.108.84:9876;10.143.108.85:9876";
    String topic = "mylc";
    String tags = "bill";
    String consumerGroup = "consumer-group";
    String producerGroup = "producer-group";
    String consumerInstance = "consumer-instance";
    String producerInstance = "producer-instance";
}
