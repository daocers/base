package com.alipay.sdk.wappay.model.builder;

import com.alipay.api.domain.AlipayTradeOrderMobilepayModel;
import com.alipay.api.internal.util.json.JSONWriter;
import com.alipay.api.request.AlipayTradeWapPayRequest;

public class AlipayWapPayRequestBuilder {
    private AlipayTradeOrderMobilepayModel model   = new AlipayTradeOrderMobilepayModel();
    private AlipayTradeWapPayRequest       request = new AlipayTradeWapPayRequest();

    public AlipayTradeOrderMobilepayModel getModel() {
        return model;
    }

    public void setModel(AlipayTradeOrderMobilepayModel model) {
        this.model = model;
    }

    public AlipayTradeWapPayRequest getRequest() {
        request.setBizContent(new JSONWriter().write(model, true));
        return request;
    }

    public void setRequest(AlipayTradeWapPayRequest request) {
        this.request = request;
    }

    public AlipayWapPayRequestBuilder setReturnUrl(String returnUrl) {
        request.setReturnUrl(returnUrl);
        return this;
    }

    public AlipayWapPayRequestBuilder setNotifyUrl(String notifyUrl) {
        request.setNotifyUrl(notifyUrl);
        return this;
    }

    public AlipayWapPayRequestBuilder setOutTradeNo(String outTradeNo) {
        model.setOutTradeNo(outTradeNo);
        return this;
    }

    public AlipayWapPayRequestBuilder setTotalAmount(String totalAmount) {
        model.setTotalAmount(totalAmount);
        return this;
    }

    public AlipayWapPayRequestBuilder setSubject(String subject) {
        model.setSubject(subject);
        return this;
    }

    public AlipayWapPayRequestBuilder setBody(String body) {
        model.setBody(body);
        return this;
    }

    public AlipayWapPayRequestBuilder setSellerId(String sellerId) {
        model.setSellerId(sellerId);
        return this;
    }

    public AlipayWapPayRequestBuilder setTimeoutExpress(String timeoutExpress) {
        model.setTimeoutExpress(timeoutExpress);
        return this;
    }

    public AlipayWapPayRequestBuilder setAuthToken(String authToken) {
        model.setAuthToken(authToken);
        return this;
    }
}
