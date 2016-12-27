package co.bugu.wechat.model.request;

/**
 * Created by daocers on 2016/7/28.
 * 文本消息
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
