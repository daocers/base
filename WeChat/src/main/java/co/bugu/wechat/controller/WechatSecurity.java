package co.bugu.wechat.controller;

import co.bugu.wechat.centerControl.EventDispatcher;
import co.bugu.wechat.centerControl.MsgDispatcher;
import co.bugu.wechat.util.MessageUtil;
import co.bugu.wechat.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by daocers on 2016/7/28.
 */
@Controller
@RequestMapping("/wechat")
public class WechatSecurity {
    private static Logger logger = LoggerFactory.getLogger(WechatSecurity.class);

    /**
     * @param @param request
     * @param @param response
     * @param @param signature
     * @param @param timestamp
     * @param @param nonce
     * @param @param echostr
     * @Description: 用于接收 get 参数，返回验证参数
     * @author dapengniao
     * @date 2016 年 3 月 4 日 下午 6:20:00
     */
    @RequestMapping(value = "/security", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(
            HttpServletRequest request,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            logger.debug("收到微信验证参数");
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                return echostr;
            } else {
                String ip = request.getRemoteAddr();
                logger.info("这里存在非法请求！请求ip为： {}", ip);
            }
        } catch (Exception e) {
            logger.error("服务器校验失败", e);
        }
        return "";
    }

    /**
     * js回调接口
     * @return
     */
    @RequestMapping("/callback")
    @ResponseBody
    public String jsCallBack(HttpServletRequest request, HttpServletResponse response){




        return "";
    }


    /**
     * 用于处理微信服务端推送的消息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/security", method = RequestMethod.POST)
    @ResponseBody
    // post 方法用于接收微信服务端消息
    public String DoPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("这是 post 方法！");
        try{
            Map<String, String> data = MessageUtil.parseXml(request);
            logger.debug("接收到微信消息: \n{}", data);
            String msgId = data.get("MsgId");
            String msgType = data.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
                logger.debug("开始进入事件消息处理");
                return EventDispatcher.processEvent(data);//进入事件处理
            }else {
                logger.debug("开始进入一般消息处理");
                return MsgDispatcher.processMessage(data);//进入消息处理
            }

        }catch (Exception e){
            logger.error("处理微信信息失败", e);
        }
        return null;
    }

}
