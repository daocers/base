package com.alipay.sdk.wappay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.alipay.sdk.wappay.AlipayClientFactory;
import com.alipay.sdk.wappay.AlipayConfigs;
import com.alipay.sdk.wappay.model.AlipayPayResult;
import com.alipay.sdk.wappay.model.builder.AlipayWapPayRequestBuilder;
import com.alipay.sdk.wappay.service.AlipayWapPayService;

public class AlipayWapPayServiceImpl implements AlipayWapPayService {

    @Override
    public String wapPay(String outTradeNo, String amount,
                         String subject) throws AlipayApiException {
        AlipayWapPayRequestBuilder builder = new AlipayWapPayRequestBuilder();
        builder.setReturnUrl(AlipayConfigs.RETURN_URL).setNotifyUrl(AlipayConfigs.NOTIFY_URL)
            .setSellerId(AlipayConfigs.SELLER_ID).setTimeoutExpress(AlipayConfigs.TIMEOUT_EXPRESS)
            .setOutTradeNo(outTradeNo).setTotalAmount(amount).setSubject(subject);
        return wapPay(builder);
    }

    @Override
    public String wapPay(AlipayWapPayRequestBuilder builder) throws AlipayApiException {
        AlipayConfigs.RETURN_URL = "http://www.baidu.com";
        AlipayConfigs.NOTIFY_URL = "http://520622a1.nat123.net/payment/notify.do";
        AlipayConfigs.SELLER_ID = "2088102176635890";
        AlipayConfigs.TIMEOUT_EXPRESS = "10d";
        builder.setReturnUrl(AlipayConfigs.RETURN_URL).setNotifyUrl(AlipayConfigs.NOTIFY_URL)
            .setSellerId(AlipayConfigs.SELLER_ID).setTimeoutExpress(AlipayConfigs.TIMEOUT_EXPRESS);
        AlipayClient alipayClient = AlipayClientFactory.getClient();
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(builder.getRequest());
        String url = null;
        if (response != null) {
            url = response.getBody();
        }
        return url;
    }

    @Override
    public AlipayPayResult getReturnResult(Map<String, String> paramsMap) throws AlipayApiException {
        return getReturnResult(paramsMap, null);
    }

    @Override
    public AlipayPayResult getReturnResult(Map<String, String> paramsMap,
                                           List<String> excludeKeys) throws AlipayApiException {
        AlipayPayResult result = new AlipayPayResult();
        if (excludeKeys != null && excludeKeys.size() > 0) {
            result.setUdfParams(new HashMap<String, String>());
            for (String key : excludeKeys) {
                if (!StringUtils.isEmpty(key)) {
                    result.getUdfParams().put(key, paramsMap.remove(key));
                }
            }
        }
        try {
            if (!AlipaySignature.rsaCheckV1(paramsMap, AlipayConfigs.ALIPAY_PUBLIC_KEY,
                AlipayConfigs.CHARSET)) {
                throw new AlipayApiException("验签失败，请检查支付宝公钥是否配置正确！");
            }
        } catch (AlipayApiException e) {
            throw new AlipayApiException("验签失败，请检查支付宝公钥是否配置正确！");
        }
        result.setResultType(AlipayPayResult.TYPE_RETURN);
        result.setIsSuccess(paramsMap.get("is_success"));
        result.setOutTradeNo(paramsMap.get("out_trade_no"));
        result.setTradeNo(paramsMap.get("trade_no"));
        result.setTotalAmount(paramsMap.get("total_amount"));
        result.setSellerId(paramsMap.get("seller_id"));
        result.setAppId(paramsMap.get("app_id"));
        return result;
    }

    @Override
    public AlipayPayResult getNotifyResult(Map<String, String> paramsMap) throws AlipayApiException {
        return getNotifyResult(paramsMap, null);
    }

    @Override
    public AlipayPayResult getNotifyResult(Map<String, String> paramsMap,
                                           List<String> excludeKeys) throws AlipayApiException {
        AlipayPayResult result = new AlipayPayResult();
        if (excludeKeys != null && excludeKeys.size() > 0) {
            result.setUdfParams(new HashMap<String, String>());
            for (String key : excludeKeys) {
                if (!StringUtils.isEmpty(key)) {
                    result.getUdfParams().put(key, paramsMap.remove(key));
                }
            }
        }
        try {
            if (!AlipaySignature.rsaCheckV1(paramsMap, AlipayConfigs.ALIPAY_PUBLIC_KEY,
                AlipayConfigs.CHARSET)) {
                throw new AlipayApiException("验签失败，请检查支付宝公钥是否配置正确！");
            }
        } catch (AlipayApiException e) {
            throw new AlipayApiException("验签失败，请检查支付宝公钥是否配置正确！");
        }
        result.setResultType(AlipayPayResult.TYPE_RETURN);
        result.setOutTradeNo(paramsMap.get("out_trade_no"));
        result.setTradeNo(paramsMap.get("trade_no"));
        result.setTotalAmount(paramsMap.get("total_amount"));
        result.setSellerId(paramsMap.get("seller_id"));
        result.setAppId(paramsMap.get("app_id"));
        result.setTradeStatus(paramsMap.get("trade_status"));
        result.setRefundFee(paramsMap.get("refund_fee"));
        result.setSubject(paramsMap.get("subject"));
        result.setBody(paramsMap.get("body"));
        result.setGmtCreate(paramsMap.get("gmt_create"));
        result.setGmtPayment(paramsMap.get("gmt_payment"));
        result.setGmtClose(paramsMap.get("gmt_close"));
        result.setGmtRefund(paramsMap.get("gmt_refund"));
        result.setBuyerId(paramsMap.get("buyer_id"));
        return result;
    }

}
