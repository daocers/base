package co.bugu.wechat.model.menu;

import co.bugu.wechat.service.IAccessTokenService;
import co.bugu.wechat.util.WeHttpUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by daocers on 2016/7/30.
 */
public class MenuMain {
    @Autowired
    IAccessTokenService accessTokenService;

    public static void main(String[] args) {

        ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");

        ViewButton vbt=new ViewButton();
        vbt.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb4eaf9e9d8c8c2ba" +
                "&redirect_uri=http://520622a1.nat123.net/callback.do" +
                "&response_type=code&scope=snsapi_userinfo" +
                "&state=1#wechat_redirect");
        vbt.setName("博客");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);

        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
//        button.add(vbt);
        button.add(buttonOne);
//        button.add(cbt);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        //这里为请求接口的 url   +号后面的是 token，这里就不做过多对 token 获取的方法解释
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                +"ZGyphHTHRkYKZlXVKaXsQVcEpeQ-8Ifbu0rc1l4Ut1b5CLjTTMHoTXIjmUZ4fKyFy4i70aRB_LEgjk8JHImuHpWQ8QA4EgRCypprY-mzNN3hQAplB0C1w5GCHJTDfP89HTTcAAADPP";

        try{
            String rs= WeHttpUtils.sendPostBuffer(url, menujson.toJSONString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }

    }
}
