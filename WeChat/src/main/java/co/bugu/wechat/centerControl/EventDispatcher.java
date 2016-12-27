package co.bugu.wechat.centerControl;

import co.bugu.wechat.model.request.ImageMessage;
import co.bugu.wechat.model.response.Image;
import co.bugu.wechat.util.MessageUtil;
import co.bugu.wechat.util.WeHttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daocers on 2016/7/28.
 */
public class EventDispatcher {
    private static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    public static String processEvent(Map<String, String> map) {
        String event = map.get("Event");
        String openid = map.get("FromUserName"); // 用户 openid
        String mpid = map.get("ToUserName"); // 公众号原始 ID
        ImageMessage imgmsg = new ImageMessage();
        imgmsg.setToUserName(openid);
        imgmsg.setFromUserName(mpid);
        imgmsg.setCreateTime(new Date().getTime());
        imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);
        if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            System.out.println("==============这是关注事件！");
            Image img = new Image();
//            HttpPostUploadUtil util=new HttpPostUploadUtil();
            String filepath="H:\\1.jpg";
            Map<String, String> textMap = new HashMap<String, String>();
            textMap.put("name", "testname");
            Map<String, String> fileMap = new HashMap<String, String>();
            fileMap.put("userfile", filepath);
//            String mediaidrs = WeHttpUtils.formUpload(textMap, fileMap);
//            System.out.println(mediaidrs);
//            String mediaid= ((JSONObject)JSON.parse(mediaidrs)).getString("media_id");
//            img.setMediaId(mediaid);
//            imgmsg.setImage(img);
//            return MessageUtil.imageMessageToXml(imgmsg);


        } else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        } else if (event.equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            System.out.println("==============这是扫描二维码事件！");
        } else if (event.equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            System.out.println("==============这是位置上报事件！");
        } else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");
        } else if (event.equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
            System.out.println("==============这是自定义菜单 View 事件！");
        }

        return null;
    }
}
