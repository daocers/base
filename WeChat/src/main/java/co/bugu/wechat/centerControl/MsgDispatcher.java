package co.bugu.wechat.centerControl;

import co.bugu.wechat.model.request.VoiceMessage;
import co.bugu.wechat.model.response.Article;
import co.bugu.wechat.model.response.NewsMessage;
import co.bugu.wechat.model.response.TextMessage;
import co.bugu.wechat.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by daocers on 2016/7/28.
 * 消息业务处理分发器
 */
public class MsgDispatcher {
    private static Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);

    public static String processMessage(Map<String, String> data) {
        String msgType = data.get("MsgType");
        String openid = data.get("FromUserName"); //用户 openid
        String mpid = data.get("ToUserName");   //公众号原始 ID
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");


            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            String content=data.get("Content");
            if("1".equals(content)){
                txtmsg.setContent("你好，你发送的内容是 1！");
            }else if("2".equals(content)){
                txtmsg.setContent("你好，你发送的内容是 2！");
            }else if("3".equals(content)){
                txtmsg.setContent("你好，你发送的内容是 3！");
            }else if("4".equals(content)){
                txtmsg.setContent("<a href=\"http://www.bugu.co\">有家酒馆</a>");
            }else{
                txtmsg.setContent("你好，欢迎来到信阳有家酒馆！");
            }
            String res = MessageUtil.textMessageToXml(txtmsg);
            logger.debug("返回消息：{}", res);
            return res;

//
//            txtmsg.setContent("您好，这是daocers 的个人账号！");
//            return MessageUtil.textMessageToXml(txtmsg);

        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
            //对图文消息
            NewsMessage newmsg=new NewsMessage();
            newmsg.setToUserName(openid);
            newmsg.setFromUserName(mpid);
            newmsg.setCreateTime(new Date().getTime());
            newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

            Article article=new Article();
            article.setDescription("这是图片消息 1"); //图文消息的描述
            article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); //图文消息图片地址
            article.setTitle("图片消息 1");  //图文消息标题
            article.setUrl("http://www.cuiyongzhi.com");  //图文 url 链接
            List<Article> list=new ArrayList<Article>();
            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
            newmsg.setArticleCount(list.size());
            newmsg.setArticles(list);
            return MessageUtil.newsMessageToXml(newmsg);

        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");

        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");

        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");

        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
            VoiceMessage message = new VoiceMessage();
            message.setRecognition(data.get("Recognition"));

        }

        return null;
    }

}
