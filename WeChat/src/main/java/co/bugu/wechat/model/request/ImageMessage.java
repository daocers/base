package co.bugu.wechat.model.request;

/**
 * Created by daocers on 2016/7/28.
 * 图片消息
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
