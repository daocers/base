package co.bugu.wechat.model.response;

/**
 * Created by daocers on 2016/7/28.
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
