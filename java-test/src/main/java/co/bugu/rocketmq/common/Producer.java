package co.bugu.rocketmq.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2017/2/17.
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        String content = "{\"data\":{\"billType\":55,\"billTypeName\":\"银行承兑汇票\",\"contractTemplate\":1,\"contractUrl\":\"http://file.gomemyc.com/v5/file/download?fileName=f41b6e4e1e0ec331206999c050400adc\",\"createTime\":1493777870000,\"delFlag\":0,\"expiringDate\":1498406400000,\"ext\":\"{\\\"acceptanceBank\\\":\\\"句容农商行\\\"}\",\"extMap\":{\"acceptanceBank\":\"句容农商行\"},\"extendDeadline\":3,\"extendExpiringDate\":1498665600000,\"id\":272,\"incrementAmount\":1.00,\"issueAmount\":99334.00,\"maxInvestAmount\":0.00,\"maxInvestNum\":0,\"maxInvestTotalAmount\":0.00,\"modifyTime\":1493790211000,\"newOrOldType\":0,\"newOrOldTypeStr\":\"资产提供方\",\"nterestMode\":78,\"planAnnualYield\":4.60,\"productCode\":\"PJCP20170503000002\",\"productDeadline\":52,\"productDesc\":\"\\r\\n\n" +
                "\\r\\n\n" +
                "\\r\\n\n" +
                "\\\"\\\"\n" +
                "\\r\\n固定期限\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "\\\"\\\"\n" +
                "\\r\\n到期一次性支付\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "\\\"\\\"\n" +
                "\\r\\n优质资产\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                " \n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "\\r\\n\n" +
                "承兑银行\n" +
                "\\r\\n\\r\\n\n" +
                "句容农商行\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "起息日期\n" +
                "\\r\\n\\r\\n\n" +
                "2017-05-05\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "到期日期\n" +
                "\\r\\n\\r\\n\n" +
                "2017-06-26\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "起投金额\n" +
                "\\r\\n\\r\\n\n" +
                "1000元\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "递增金额\n" +
                "\\r\\n\\r\\n\n" +
                "1元\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "募集时间\n" +
                "\\r\\n\\r\\n\n" +
                "2017-05-03至2017-05-04\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "票据类型\n" +
                "\\r\\n\\r\\n\n" +
                "银行承兑汇票\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "赎回说明\n" +
                "\\r\\n\\r\\n\n" +
                "投资人一旦购买成功，则在产品未到期前不得赎回\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "宽限期\n" +
                "\\r\\n\\r\\n\n" +
                "3天（宽限期内不计息，节假日顺延）\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                "\\r\\n\n" +
                "产品协议\n" +
                "\\r\\n\\r\\n\n" +
                "【查看产品协议】 【个人会员服务协议】 投资人确认居间人美易理财已就协议各方的权利限制及处分、责任限制及免除、义务增加等重点条款进行了 充分、准确、有效的说明，投资人对全部条款不存在任何疑问且同意在各方对协议或交易项目理解不一致时以居间人的解释为准\n" +
                "\\r\\n\n" +
                "\\r\\n\n" +
                "\\r\\n\\r\\n\n" +
                " \n" +
                "\\r\\n\\r\\n\n" +
                "风险提示：投资具有一定的风险，可能导致投资人无法实现预期收益乃至本金遭受损失。投资人购买前应完全理解投资性质与风险，审慎评估与决策。投资人需对自己的投资行为承担全部责任， 并承担全部风险。请您认真阅读并理解《风险提示函》\n" +
                "\\r\\n\\r\\n\n" +
                " \n" +
                "\\r\\n\\r\\n\n" +
                "银行承兑汇票由承兑银行无条件见票即付，有银行信用背书。票据权利通过相关协议约定，质权人为美易理财线上投资人，享有到期收益的权利。\n" +
                "\\r\\n\\r\\n\n" +
                " \n" +
                "\\r\\n\\r\\n\n" +
                "平台免责声明：美易理财作为交易服务平台进行信息发布，不对任何投资人及/或任何交易提供任何担保,无论是明示、默示或法定的。美易理财平台提供的各种信息及资料仅供参考,投资人应依其独立判断做出决策。投资人据此进行投资交易的,产生的投资风险由投资人自行承担。\n" +
                "\\r\\n\",\"productName\":\"美票宝-17050302\",\"productType\":1,\"productTypeStr\":\"票据\",\"pushPartyId\":119,\"raiseEndDate\":1493827200000,\"raiseStartDate\":1493740800000,\"refundSource\":2,\"refundSourceStr\":\"债权人还款\",\"startBidAmount\":1000.00,\"status\":1,\"statusStr\":\"已推送\",\"valueDate\":1493913600000},\"type\":\"sendProduct\"}";
        Logger logger = LoggerFactory.getLogger(Producer.class);
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer("zgSystemProducer");
        producer.setNamesrvAddr("10.143.88.73:9876;10.143.88.74:9876;10.143.88.75:9876");
//        producer.setNamesrvAddr("10.143.88.76:9876");
//        producer.setNamesrvAddr("192.168.1.128:9876");
        producer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < 3; i++) {
        try {
            /**
             * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
             * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
             * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
             * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
             */
//                JSONObject json = new JSONObject();
//                json.put("id", 217);
//                json.put("invest_rate", 4.6);
//                json.put("status", 3);
//                json.put("factraise_time", 1492151400000L);
//                json.put("type", "productStatus");
                Message msg = new Message("mylc",// topic
                        "bill",// tag
                        "send" + System.currentTimeMillis()/1000,// key
                        content.getBytes());// body
                SendResult sendResult = producer.send(msg);
                System.out.println("发送状态" +sendResult.getSendStatus().name());
                System.out.println("消息id：" + sendResult.getMsgId());
                System.out.println(sendResult);

//            Thread.sleep(1000);
//            JSONObject liubiao = new JSONObject();
//            liubiao.put("id", 217);
//            liubiao.put("status", 5);
//            liubiao.put("type", "productStatus");
//            msg = new Message("zgsystem",// topic
//                    "bill",// tag
//                    "liubiao" + System.currentTimeMillis() / 1000,// key
//                    JSON.toJSONString(liubiao).getBytes());// body
//            sendResult = producer.send(msg);
//            System.out.println("发送状态" + sendResult.getSendStatus().name());
//            System.out.println("消息id：" + sendResult.getMsgId());
//            System.out.println(sendResult);
//
//            JSONObject jiebiao = new JSONObject();
//            jiebiao.put("id", null);

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
//        }
        /**
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出销毁方法里调用shutdown方法
         */
        producer.shutdown();
    }
}
