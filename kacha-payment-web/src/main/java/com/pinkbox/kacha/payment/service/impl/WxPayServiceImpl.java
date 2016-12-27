package com.pinkbox.kacha.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.pinkbox.kacha.payment.constant.PaymentConfig;
import com.pinkbox.kacha.payment.domain.wechat.*;
import com.pinkbox.kacha.payment.service.IWxPayService;
import com.pinkbox.kacha.payment.util.HttpUtil;
import com.pinkbox.kacha.payment.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;

/**
 * Created by daocers on 2016/9/23.
 */
@Service
public class WxPayServiceImpl implements IWxPayService {
    private static Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
    @Override
    public UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException {
        String url = PaymentConfig.getWx("url_unified_order");
        return processRequest(url, request, UnifiedOrderResponse.class);
    }

    @Override
    public QueryResponse queryOrder(QueryRequest queryRequest) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException {
        String url = PaymentConfig.getWx("url_query_order");
        return processRequest(url, queryRequest, QueryResponse.class);
    }

    @Override
    public CloseOrderResponse closeOrder(CloseOrderRequest closeOrderRequest) throws NoSuchMethodException, GeneralSecurityException, IllegalAccessException, IOException, InvocationTargetException, ClassNotFoundException {
        String url = PaymentConfig.getWx("url_close_order");
        return processRequest(url, closeOrderRequest, CloseOrderResponse.class);
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) throws NoSuchMethodException, GeneralSecurityException, IllegalAccessException, IOException, InvocationTargetException, ClassNotFoundException {
        String url = PaymentConfig.getWx("url_refund_order");
        return processRequest(url, refundRequest, RefundResponse.class);
    }

    private <T> T processRequest(String url, Object request, Class<T> tClass) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException {
        String alias = XmlUtil.getAliasOfClass(tClass);

        String xml = XmlUtil.objToXml(request, XmlUtil.FIELD_POLICY_UNDERLINE);
        logger.debug("请求报文：\n {}", xml);
        String res = HttpUtil.postXml(url, xml, HttpUtil.UTF8, HttpUtil.connTimeout, HttpUtil.readTimeout);
        logger.debug("获取到微信响应报文： \n {}", res);
        res = res.replaceAll("xml", alias);
        T obj = XmlUtil.xmlToObj(res, tClass, XmlUtil.FIELD_POLICY_UNDERLINE);
        return obj;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException {
        String res = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wxf538fa1c9eb1f4de]]></appid>\n" +
                "<mch_id><![CDATA[1361086102]]></mch_id>\n" +
                "<nonce_str><![CDATA[zCmyGbI5X0mB0Qqy]]></nonce_str>\n" +
                "<sign><![CDATA[747C7DF62D98F3024E93610F2FF6E48E]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<openid><![CDATA[ozpsDwko9YmMqXcui_BcHjJ-b4A8]]></openid>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<total_fee>1</total_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<transaction_id><![CDATA[4001372001201610176943090232]]></transaction_id>\n" +
                "<out_trade_no><![CDATA[2016101716554647401000072]]></out_trade_no>\n" +
                "<attach><![CDATA[]]></attach>\n" +
                "<time_end><![CDATA[20161017165604]]></time_end>\n" +
                "<trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
                "<cash_fee>1</cash_fee>\n" +
                "</xml>";
        res = res.replaceAll("xml", "query_response");
        QueryResponse response = XmlUtil.xmlToObj(res, QueryResponse.class, XmlUtil.FIELD_POLICY_UNDERLINE);
    }
}
