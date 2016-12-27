package com.alipay.sdk.wappay.model;

import java.util.Map;

public class AlipayPayResult {
    public static final String  TYPE_RETURN    = "RETURN";
    public static final String  TYPE_NOTIFY    = "NOTIFY";
    public static final String  TRADE_FINISHED = "TRADE_FINISHED";
    public static final String  TRADE_SUCCESS  = "TRADE_SUCCESS";
    public static final String  WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    public static final String  TRADE_CLOSED   = "TRADE_CLOSED";

    private String              resultType;
    private String              isSuccess;
    private String              tradeNo;
    private String              appId;
    private String              outTradeNo;
    private String              sellerId;
    private String              tradeStatus;
    private String              totalAmount;
    private String              refundFee;
    private String              subject;
    private String              body;
    private String              gmtCreate;
    private String              gmtPayment;
    private String              gmtRefund;
    private String              gmtClose;
    private String              buyerId;
    private Map<String, String> udfParams;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public String getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(String gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public String getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(String gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Map<String, String> getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(Map<String, String> udfParams) {
        this.udfParams = udfParams;
    }
}
