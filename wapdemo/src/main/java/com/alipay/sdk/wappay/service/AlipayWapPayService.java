package com.alipay.sdk.wappay.service;

import java.util.List;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.sdk.wappay.model.AlipayPayResult;
import com.alipay.sdk.wappay.model.builder.AlipayWapPayRequestBuilder;

public interface AlipayWapPayService {
    public String wapPay(String outTradeNo, String amount,
                         String subject) throws AlipayApiException;

    public String wapPay(AlipayWapPayRequestBuilder builder) throws AlipayApiException;

    public AlipayPayResult getReturnResult(Map<String, String> paramsMap) throws AlipayApiException;

    public AlipayPayResult getReturnResult(Map<String, String> paramsMap,
                                           List<String> excludeKeys) throws AlipayApiException;

    public AlipayPayResult getNotifyResult(Map<String, String> paramsMap) throws AlipayApiException;

    public AlipayPayResult getNotifyResult(Map<String, String> paramsMap,
                                           List<String> excludeKeys) throws AlipayApiException;
}
