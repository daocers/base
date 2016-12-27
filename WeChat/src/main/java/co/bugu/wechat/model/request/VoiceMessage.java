package co.bugu.wechat.model.request;

/**
 * Created by daocers on 2016/7/28.
 * 语音消息
 */
public class VoiceMessage extends BaseMessage {
    // 媒体 ID
    private String MediaId;
    // 语音格式
    private String Format;

    //语音识别
    private String Recognition;

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
