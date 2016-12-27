package co.bugu.wechat.model.menu;

/**
 * Created by daocers on 2016/7/30.
 * 点击型菜单事件
 */
public class ClickButton {
    private String type;
    private String name;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
