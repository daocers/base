package com.pinkbox.kacha.payment.domain.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询订单请求参数
 * Created by daocers on 2016/9/23.
 */
@XStreamAlias("xml")
public class QueryRequest {
    private String appid;
    private String mchId;
    private String transactionId;
    private String outTradeNo;
    private String nonceStr;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
