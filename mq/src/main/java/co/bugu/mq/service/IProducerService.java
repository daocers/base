package co.bugu.mq.service;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by user on 2017/5/5.
 */
public interface IProducerService {
    SendResult send(String topic, String tags, String body, String key) throws InterruptedException, RemotingException, MQClientException, MQBrokerException;
}
