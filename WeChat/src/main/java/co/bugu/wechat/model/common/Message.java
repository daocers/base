package co.bugu.wechat.model.common;

import java.util.Date;

/**
 * Created by daocers on 2016/9/18.
 */
public class Message {
    private Integer type; //1 接收到， 0 发送出
    private String targetUser;//用户的openid或者unionid
    private Date createTime;
    private String msgType;//消息类型  文本 语音图文等
    private Integer dataType;//数据类型 0 json， 1 xml
    private String content;//消息数据
    private Integer starFlag;//星标消息 0 是， 1 否

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStarFlag() {
        return starFlag;
    }

    public void setStarFlag(Integer starFlag) {
        this.starFlag = starFlag;
    }
}
