package com.pinkbox.kacha.payment.controller;

import co.bugu.framework.core.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.pinkbox.kacha.payment.constant.PaymentConfig;
import com.pinkbox.kacha.payment.domain.wechat.*;
import com.pinkbox.kacha.payment.service.IUnifiedorderrequestService;
import com.pinkbox.kacha.payment.service.IUnifiedorderresponseService;
import com.pinkbox.kacha.payment.service.IWxPayService;
import com.pinkbox.kacha.payment.service.IWxnotifyrequestService;
import com.pinkbox.kacha.payment.util.DateUtil;
import com.pinkbox.kacha.payment.util.XmlUtil;
import com.pinkbox.kacha.payment.util.wechat.RandomStringGenerator;
import com.pinkbox.kacha.payment.util.wechat.Signature;
import com.pinkbox.kacha.retailer.model.MallOrder;
import com.pinkbox.kacha.retailer.model.MallOrderLog;
import com.pinkbox.kacha.retailer.service.IMallOrderService;
import com.pinkbox.kacha.retailer.util.OrderConstant;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 * Created by daocers on 2016/9/23.
 */
@RestController
@RequestMapping("/wechat/pay")
public class WechatPayController {

    @Autowired
    IWxPayService wxPayService;

    @Autowired
    IMallOrderService orderService;

    @Autowired
    IUnifiedorderrequestService unifiedorderrequestService;

    @Autowired
    IUnifiedorderresponseService unifiedorderresponseService;

    @Autowired
    IWxnotifyrequestService wxnotifyrequestService;

    private static Logger logger = LoggerFactory.getLogger(WechatPayController.class);

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IOException, GeneralSecurityException {
//        String res = "{\"appid\":\"wxf538fa1c9eb1f4de\",\"body\":\"KA-购买商品\",\"mchId\":\"1361086102\",\"nonceStr\":\"4w7u46b35ejwbfx7fvdq92hhxk9my8io\",\"notifyUrl\":\"http://101.200.205.46:8087/kacha-payment-web/wechat/pay/notify.action\",\"outTradeNo\":\"2016101317261082401000028\",\"spbillCreateIp\":\"182.50.123.182\",\"totalFee\":19900,\"tradeType\":\"JSAPI\"}";
//        UnifiedOrderRequest request = JSON.parseObject(res, UnifiedOrderRequest.class);
//        Map<String, Object> map = getMapOfRequest(request);
//        String sign = Signature.getSign(map);
//        String xml = XmlUtil.objToXml(request, XmlUtil.FIELD_POLICY_UNDERLINE);
//        System.out.println(xml);
        String notify = "<xml><appid><![CDATA[wxf538fa1c9eb1f4de]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1361086102]]></mch_id>\n" +
                "<nonce_str><![CDATA[n7hu3udbti7n01pxxv3mmz67r2cqs3z2]]></nonce_str>\n" +
                "<openid><![CDATA[ozpsDwko9YmMqXcui_BcHjJ-b4A8]]></openid>\n" +
                "<out_trade_no><![CDATA[2016101716554647401000072]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[4CFA7BECA3578B14DAF412BC075B2367]]></sign>\n" +
                "<time_end><![CDATA[20161017165604]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4001372001201610176943090232]]></transaction_id>\n" +
                "</xml>";
        HttpUtil.postXml("http://101.200.205.46:8087/kacha-payment-web/wechat/pay/notify.action", notify,
                HttpUtil.UTF8, 5000, 5000);
    }

    /**
     * 微信h5支付，获取调用客户端的参数信息
     * 直接使用就行
     *
     * @return
     */
    @RequestMapping("/jsPay")
    public String h5Pay(HttpServletRequest req, String orderCode, String openid) {
        Map<String, Object> res = new HashMap<>();
        try {
            logger.debug("收到微信H5支付请求,准备为其生成唤起微信支付界面的参数，订单编号：{}, openid: {}", new String[]{orderCode, openid});
            UnifiedOrderRequest request = new UnifiedOrderRequest();
            MallOrder order = orderService.selectSimple(orderCode);
            logger.debug("获取到订单，{}", JSON.toJSONString(order, true));
            request.setAppid(PaymentConfig.getWx("appid"));
            request.setMchId(PaymentConfig.getWx("mch_id"));
            request.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
            request.setBody("咖嚓-购买商品");
//            request.setOutTradeNo();
            request.setTotalFee((int) (order.getPrice() * 100));
            request.setSpbillCreateIp(req.getRemoteAddr());
            request.setNotifyUrl(PaymentConfig.getWx("notify_url"));
            request.setTradeType("JSAPI");
            request.setOutTradeNo(orderCode);
            request.setOpenid(openid);
            Map<String, Object> map = getMapOfRequest(request);
            logger.debug("代签名实体：\n {}", JSON.toJSONString(request));
            String sign = Signature.getSign(map);
            logger.debug("生成签名：\n {}", sign);
            request.setSign(sign);
            logger.debug("准备发起统一下单请求， 请求参数：\n{}", JSON.toJSONString(request, true));
            UnifiedOrderRequest unifiedOrderRequest = unifiedorderrequestService.selectById(orderCode);
            if(unifiedOrderRequest == null){
                unifiedorderrequestService.save(request);
            }
            UnifiedOrderResponse response = wxPayService.unifiedOrder(request);
            logger.debug("统一下单请求成功，响应数据：\n{}", JSON.toJSONString(response, true));
//            UnifiedOrderResponse unifiedOrderResponse = unifiedorderresponseService.selectById(response.getPrepayId());
//            if(unifiedOrderResponse == null){
//                unifiedorderresponseService.save(response);
//            }
            if(response.getReturnCode().equals("SUCCESS")){
//                WxJsResponse jsResponse = new WxJsResponse();
//                jsResponse.setOrderPackage("prepay_id=" + response.getPrepayId());
//                jsResponse.setAppId(request.getAppid());
//                jsResponse.setTimeStamp(DateUtil.getTimestampOfSecond() + "");
//                jsResponse.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
//                jsResponse.setSignType("MD5");
//                jsResponse.setPaySign(Signature.getSign(jsResponse));
                Map<String, Object> jsRes = new HashMap<>();
                jsRes.put("appId", request.getAppid());
                jsRes.put("timeStamp", new Date().getTime() / 1000 + "");
                logger.debug((String) jsRes.get("timeStamp") + "\n");
                jsRes.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
                jsRes.put("package", "prepay_id=" + response.getPrepayId());
                jsRes.put("signType", "MD5");
                jsRes.put("paySign", Signature.getSign(jsRes));
                res.put("errNo", 0);
                res.put("data", jsRes);
                return JSON.toJSONString(res, true);
            }else{
                logger.error("统一下单请求失败", JSON.toJSONString(response, true));
                res.put("errNo", -1);
                res.put("errMsg", "统一下单失败");
                return JSON.toJSONString(res, true);
            }

        } catch (Exception e) {
            logger.error("调用统一下单失败", e);
            res.put("errNo", -1);
            res.put("errMsg", "调用统一下单失败");
        }
        return JSON.toJSONString(res, true);
    }

    private static Map<String,Object> getMapOfRequest(UnifiedOrderRequest request) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = request.getClass().getDeclaredFields();
        for(Field field: fields){
            field.setAccessible(true);
            String name = toUnderLine(field.getName());
            Object value = field.get(request);
            if(value != null && !value.equals("")){
                map.put(name, value);
            }
        }
        return map;
    }

    public static String toUnderLine(String className){
        if(StringUtils.isEmpty(className)){
            return "";
        }
        if(className.length() == 1){
            return className.toLowerCase();
        }
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        StringBuilder builder = new StringBuilder();
        for(char c: className.toCharArray()){
            if((c >= 'A' && c <= 'Z') || c == '$'){
                builder.append('_')
                        .append((c + "").toLowerCase());
            }else{
                builder.append(c);
            }
        }
        return builder.toString();
    }

    @RequestMapping("/query")
    public String query() {
        return null;
    }

    /**
     * 微信服务器异步通知
     *
     * @return
     */
    @RequestMapping("/notify")
    public String callbackNotify(HttpServletRequest request) {
        try {
            String res = IOUtils.toString(request.getInputStream(), "utf-8");
            logger.debug("收到微信支付通知，通知信息： \n{}", res);
            WxNotifyRequest wxNotifyRequest = XmlUtil.xmlToObj(res, WxNotifyRequest.class, XmlUtil.FIELD_POLICY_UNDERLINE);
            wxnotifyrequestService.save(wxNotifyRequest);
            if (wxNotifyRequest.getReturnCode().equals("SUCCESS") && wxNotifyRequest.getResultCode().equals("SUCCESS")) {
                //通知上说支付成功，请求验证
                QueryRequest queryRequest = new QueryRequest();
                queryRequest.setAppid(wxNotifyRequest.getAppid());
                queryRequest.setMchId(wxNotifyRequest.getMchId());
                queryRequest.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
                queryRequest.setTransactionId(wxNotifyRequest.getTransactionId());
                Map<String, Object> map = new HashMap<>();
                map.put("appid", wxNotifyRequest.getAppid());
                map.put("mch_id", wxNotifyRequest.getMchId());
                map.put("nonce_str", queryRequest.getNonceStr());
                map.put("transaction_id", wxNotifyRequest.getTransactionId());
//                map.put("out_trade_no", wxNotifyRequest.getOutTradeNo());
                String sign = Signature.getSign(map);

                queryRequest.setSign(sign);
                QueryResponse queryResponse = wxPayService.queryOrder(queryRequest);
                if (queryResponse.getReturnCode().equals(queryResponse.getResultCode())
                        && queryResponse.getReturnCode().equals("SUCCESS")) {
                    //支付成功，通知订单修改状态
                    MallOrder order = new MallOrder();
                    order.setOrderCode(wxNotifyRequest.getOutTradeNo());
                    order = orderService.selectById(order);
                    order.setOrderStatus(OrderConstant.ORDER_PAID);

                    MallOrderLog log = new MallOrderLog();
                    log.setOrderId(order.getOrderId());
                    log.setInfo("支付通知，完成支付");
                    log.setType(OrderConstant.LOG_OPR_PAY);
                    log.setCreateTime(new Date());
                    log.setUserId(0);
                    orderService.update(order, log);
                    logger.debug("支付通知处理完毕，订单更新完毕");
                } else {
                    logger.warn("微信支付异步通知显示成功，查询订单显示支付失败，请校验。微信订单号：\n{}",
                            wxNotifyRequest.getTransactionId());
                }


            } else {//支付失败，处理
                logger.warn("支付失败", JSON.toJSONString(request, true));
            }
        } catch (Exception e) {
            logger.error("处理微信异步通知失败", e);
        }
        return null;
    }
}
