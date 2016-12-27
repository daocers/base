package co.bugu.pay.controller;

import co.bugu.framework.util.DateUtil;
import co.bugu.framework.util.XmlUtil;
import co.bugu.pay.domain.wechat.UnifiedOrderRequest;
import co.bugu.pay.domain.wechat.UnifiedOrderResponse;
import co.bugu.pay.domain.wechat.WxJsResponse;
import co.bugu.pay.domain.wechat.WxNotifyRequest;
import co.bugu.pay.service.IWxPayService;
import co.bugu.pay.util.wechat.RandomStringGenerator;
import co.bugu.pay.util.wechat.Signature;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;

/**
 * 微信支付
 * Created by daocers on 2016/9/23.
 */
@RestController
@RequestMapping("/wechat/pay")
public class WechatPayController {

    @Autowired
    IWxPayService wxPayService;

    private static Logger logger = LoggerFactory.getLogger(WechatPayController.class);
    /**
     * 微信h5支付，获取调用客户端的参数信息
     * 直接使用就行
     * @return
     */
    @RequestMapping("/jsPay")
    public String h5Pay(UnifiedOrderRequest request){
        try {
            UnifiedOrderResponse response = wxPayService.unifiedOrder(request);
            WxJsResponse jsResponse = new WxJsResponse();
            jsResponse.setOrderPackage("prepay_id=" + response.getPrepayId());
            jsResponse.setAppId(request.getAppid());
            jsResponse.setTimeStamp(DateUtil.getTimestampOfSecond() + "");
            jsResponse.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
            jsResponse.setSignType("MD5");
            jsResponse.setPaySign(Signature.getSign(jsResponse));
            return JSON.toJSONString(jsResponse, true);
        } catch (Exception e) {
            logger.error("调用统一下单失败", e);
        }
        return null;
    }

    @RequestMapping("/query")
    public String query(){
        return null;
    }

    /**
     * 微信服务器异步通知
     * @return
     */
    @RequestMapping("/notify")
    public String callbackNotify(HttpServletRequest request){
        try{
            String res = IOUtils.toString(request.getInputStream(), "utf-8");
            WxNotifyRequest wxNotifyRequest = XmlUtil.xmlToObj(res, WxNotifyRequest.class, XmlUtil.FIELD_POLICY_UNDERLINE);
            if(wxNotifyRequest.getReturnCode().equals("SUCCESS") && wxNotifyRequest.getResultCode().equals("SUCCESS")){
                //支付成功，通知订单修改状态
            }else{//支付失败，处理
                logger.warn("支付失败", JSON.toJSONString(request, true));
            }
        }catch (Exception e){
            logger.error("处理微信异步通知失败", e);
        }
        return null;
    }
}
