package co.bugu.data.controller;

import co.bugu.data.mq.MQProducer;
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
    MQProducer service;

    private String body = "{\"accountBank\":\"6225880188889999\",\"accountName\":\"天津博盛保理\",\"artificialPerson\":\"张三\",\"associatedAddr\":\"\",\"associatedBusinessLicense\":\"\",\"associatedName\":\"\",\"associatedPerson\":\"\",\"bankName\":\"招商银行\",\"businessLicenseCode\":\"8888888888\",\"createTime\":1492152515000,\"delFlag\":0,\"externalRateInfo\":\"\",\"id\":34,\"modifyTime\":1492152515000,\"organizationCode\":\"88888\",\"productinfo\":\"\",\"projectinfo\":\"\",\"pushPartyAttr\":45,\"pushPartyOgrName\":\"天津博盛保理\",\"pushPartyOgrNameShort\":\"\",\"pushPartyOrgCode\":\"test123\",\"pushPartyType\":39,\"registerAddr\":\"天津\",\"remark\":\"\",\"status\":1,\"taxEnrolCode\":\"12112\",\"userId\":44}";
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
    private MQProducer service;
    private String body;
    private Integer count;

    public MQSendThread() {

    }

    public MQSendThread(MQProducer service, String body, Integer count) {
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
