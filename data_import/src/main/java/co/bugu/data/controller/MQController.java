package co.bugu.data.controller;

import co.bugu.data.service.impl.MQProducerConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/4/12.
 */
@Controller
public class MQController {
    private static Logger logger = LoggerFactory.getLogger(MQController.class);
    private static Logger mqLogger = LoggerFactory.getLogger("MQ");
    @Autowired
    MQProducerConsumerService service;

    private String body = "{\"data\":{\"billType\":55,\"billTypeName\":\"银行承兑汇票\",\"contractTemplate\":1,\"contractUrl\":\"http://file.gomemyc.com/v5/file/download?fileName=9ec62aa0d48e640e427e01a4d5122626\",\"createTime\":1491960907000,\"delFlag\":0,\"expiringDate\":1496592000000,\"ext\":\"{\\\"acceptanceBank\\\":\\\"浙江温岭农村合作银行\\\"}\",\"extMap\":{\"acceptanceBank\":\"浙江温岭农村合作银行\"},\"extendDeadline\":3,\"extendExpiringDate\":1496851200000,\"id\":197,\"incrementAmount\":1.00,\"issueAmount\":99322.00,\"maxInvestAmount\":0.00,\"maxInvestNum\":0,\"maxInvestTotalAmount\":0.00,\"modifyTime\":1491961458000,\"newOrOldType\":0,\"newOrOldTypeStr\":\"资产提供方\",\"nterestMode\":78,\"planAnnualYield\":4.60,\"productCode\":\"PJCP20170412000026\",\"productDeadline\":53,\"productDesc\":\"\\r\\n\n" +
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
            "浙江温岭农村合作银行\n" +
            "\\r\\n\n" +
            "\\r\\n\\r\\n\n" +
            "\\r\\n\n" +
            "起息日期\n" +
            "\\r\\n\\r\\n\n" +
            "2017-04-13\n" +
            "\\r\\n\n" +
            "\\r\\n\\r\\n\n" +
            "\\r\\n\n" +
            "到期日期\n" +
            "\\r\\n\\r\\n\n" +
            "2017-06-05\n" +
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
            "2017-04-12至2017-04-12\n" +
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
            "\\r\\n\",\"productName\":\"美票宝-17041202\",\"productType\":1,\"productTypeStr\":\"票据\",\"pushPartyId\":119,\"raiseEndDate\":1491926400000,\"raiseStartDate\":1491926400000,\"refundSource\":2,\"refundSourceStr\":\"债权人还款\",\"startBidAmount\":1000.00,\"status\":1,\"statusStr\":\"已推送\",\"valueDate\":1492012800000},\"type\":\"sendProduct\"}";


    @RequestMapping("/send")
    @ResponseBody
    public String send(Integer threadCount, Integer perCount) {
        if (threadCount == null || threadCount == 0) {
            threadCount = 1;
        }
        if (perCount == null || perCount == 0) {
            perCount = 1;
        }

        for (int i = 0; i < threadCount; i++) {
            new MQSendThread(service, body, perCount).start();
        }
        return null;
    }


}

class MQSendThread extends Thread {
    private Logger mqLogger = LoggerFactory.getLogger("MQ");
    private Logger logger = LoggerFactory.getLogger(MQSendThread.class);
    private MQProducerConsumerService service;
    private String body;
    private Integer count;

    public MQSendThread() {

    }

    public MQSendThread(MQProducerConsumerService service, String body, Integer count) {
        this.service = service;
        this.body = body;
        this.count = count;
    }


    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                String msgId = service.sendMsg(body);
                if (msgId == null) {
                    mqLogger.warn("消息体为空， body: {}", body);
                } else if (msgId.equals("")) {
                    mqLogger.warn("发送失败");
                } else {
                    mqLogger.info("发送成功， msgId: {}", msgId);
                }
            }


        } catch (Exception e) {
//            mqLogger.error("MQ发送失败");
            logger.error("发送失败", e);
        }
    }
}
