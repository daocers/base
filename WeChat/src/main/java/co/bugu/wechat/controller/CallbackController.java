package co.bugu.wechat.controller;

import co.bugu.wechat.model.common.AuthAccessToken;
import co.bugu.wechat.model.common.UserInfo;
import co.bugu.wechat.service.IUserInfoService;
import co.bugu.wechat.util.WechatApiUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by daocers on 2016/9/14.
 */
@Controller
public class CallbackController {
    @Autowired
    IUserInfoService userInfoService;

    private static Logger logger = LoggerFactory.getLogger(CallbackController.class);

    /**
     * 微信网页授权回调地址
     * @param request
     * @param code
     * @param modelMap
     * @return
     */
    @RequestMapping("/callback")
    public String callback(HttpServletRequest request, String code, ModelMap modelMap){
        try{
            logger.debug("接收到微信网页授权回调地址， code: {}", code);
            AuthAccessToken token = WechatApiUtil.getAccessTokenByCode(code);
            UserInfo userInfo = WechatApiUtil.getUserInfoByAccessTokenAndOpenId(token.getAccessToken(), token.getOpenid());
            logger.info("获取到用户信息， {}", JSON.toJSONString(userInfo, true));
            modelMap.put("userinfo", JSON.toJSONString(userInfo, true));
        }catch (Exception e){
            logger.error("处理授权回调失败", e);
            return "index";//返回失败页面
        }
        return "index";
    }
}
