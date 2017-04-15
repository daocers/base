package co.bugu.data.mq.listener;

import co.bugu.framework.util.JedisUtil;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by user on 2017/4/15.
 */
public class TesMessageListenerConcurrently implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for(MessageExt msg: msgs){
            String msgId = msg.getMsgId();
            JedisUtil.sadd("MQ_MSG_ID_LIST", msgId);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
