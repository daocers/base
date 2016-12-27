package co.bugu.pay.domain.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * Created by daocers on 2016/9/23.
 */
public class WxJsResponse {
    private String appId;
    private String timeStamp;
    private String nonceStr;
    @XStreamAlias("package")
    private String orderPackage;//订单详情扩展字段，对应接口的package字段
    private String signType;
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getOrderPackage() {
        return orderPackage;
    }

    public void setOrderPackage(String orderPackage) {
        this.orderPackage = orderPackage;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
