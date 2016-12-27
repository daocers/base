package co.bugu.wechat.util;

import co.bugu.framework.core.util.HttpUtil;
import co.bugu.wechat.global.Constant;
import co.bugu.wechat.model.common.AccessToken;
import co.bugu.wechat.model.common.AuthAccessToken;
import co.bugu.wechat.model.common.UserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * Created by daocers on 2016/9/13.
 * 用于请求微信公众服务器的辅助类
 */
public class WechatApiUtil {

    private static Logger logger = LoggerFactory.getLogger(WechatApiUtil.class);

    /**
     * 获取基础支持的access_token
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static AccessToken getAccessToken() throws IOException, GeneralSecurityException {
        String url = Constant.get("access_token");
        Map<String, String> param = new HashMap<>();
        param.put("grant_type", "client_credential");
        param.put("appid", Constant.get("appid"));
        param.put("secret", Constant.get("appSecret"));
        String res = HttpUtil.get(url, param);
        if(StringUtils.isEmpty(res)){
            return null;
        }else{
            JSONObject obj = JSON.parseObject(res);
            String token = obj.getString("access_token");
            Integer seconds = obj.getInteger("expires_in");
            logger.debug(res);
            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(token);
            accessToken.setExpiresIn(seconds);
            accessToken.setCreateTime(new Date());
            accessToken.setAppid(Constant.get("appid"));
            return accessToken;
        }
    }

    public static void main(String[] args) throws Exception {
//        WechatApiUtil.getAccessToken();
//        WechatApiUtil.getAccessTokenByCode("031F9nQ12jPzM21ywOO12hLmQ12F9nQq");
//        WechatApiUtil.getWechatIp();
//        WechatApiUtil.getUserInfoBasic("odNMvv9UcLpAStnBn7NMT1lLINwU");
        WechatApiUtil.generateErcode();
    }

    /**
     * 网页授权
     * 根据网页返回的code获取accessToken
     * @param code
     * @return
     */
    public static AuthAccessToken getAccessTokenByCode(String code) throws IOException, GeneralSecurityException {
        String url = Constant.get("code_to_access_token");
        Map<String, String> param = new HashMap<>();
        param.put("appid", Constant.get("appid"));
        param.put("secret", Constant.get("appSecret"));
        param.put("code", code);
        param.put("grant_type", "authorization_code");
        String res = HttpUtil.get(url, param);
        logger.debug("获取到网页授权用户access_token信息， {}", res);
        JSONObject obj = JSON.parseObject(res);
        if(obj.containsKey("errcode")){
            logger.error("获取网页授权用户access_token失败，msg: {}", obj.get("errmsg"));
            return null;
        }else{
            String accessToken = obj.getString("access_token");
            Integer expiresIn = obj.getInteger("expires_in");
            String refreshToken = obj.getString("refresh_token");
            String unionId = obj.getString("unionid");//绑定到开放平台后有该字段
            String openId = obj.getString("openid");
            AuthAccessToken token = new AuthAccessToken();
            token.setAccessToken(accessToken);
            token.setExpiresIn(expiresIn);
            token.setRefreshToken(refreshToken);
            token.setUnionid(unionId);
            token.setOpenid(openId);
            token.setCreateTime(new Date());
            return token;
        }

    }

    /**
     * 网页授权获取用户信息
     *
     * @param openId
     * @return
     */
    public static UserInfo getUserInfoByAccessTokenAndOpenId(String accessToken, String openId) throws IOException, GeneralSecurityException {
        String url = Constant.get("auth_userinfo");
        Map<String, String> param = new HashMap<>();
        param.put("access_token", accessToken);
        param.put("openid", openId);
        param.put("lang", "zh_CN");

        String res = HttpUtil.get(url, param);
        JSONObject obj = JSON.parseObject(res);
        if(obj.containsKey("errcode")){
            logger.error("获取用户信息失败: {}", obj.getString("errmsg"));
            return null;
        }else{
            UserInfo user = JSON.parseObject(res, UserInfo.class);
            return user;
        }


    }

    /**
     * 校验是否有效
     * @param  accessToken
     * @param openid
      * @return
     */
    public static boolean isValid(String accessToken, String openid) throws IOException, GeneralSecurityException {
        String url = Constant.get("check_token");
        Map<String, String> param = new HashMap<>();
        param.put("access_token", accessToken);
        param.put("openid", openid);
        String res = HttpUtil.get(url, param);
        JSONObject obj = JSON.parseObject(res);
        if(obj.getInteger("errcode") == 0){
            return true;
        }
        return false;
    }

    /**
     * 获取用户基本信息
     * @param openid
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static UserInfo getUserInfoBasic(String openid) throws IOException, GeneralSecurityException {
        AccessToken accessToken = WechatApiUtil.getAccessToken();
        if(accessToken != null){
            String url = Constant.get("basic_userinfo");
            Map<String, String> param = new HashMap<>();
            param.put("access_token", accessToken.getAccessToken());
            param.put("openid", openid);
            param.put("lang", "zh_CN");
            String res = HttpUtil.get(url, param);
            JSONObject obj = JSON.parseObject(res);
            if(obj.containsKey("errcode")){
                logger.error("获取用户信息失败, {}", obj.get("errmsg"));
                return null;
            }
            UserInfo userInfo = JSON.parseObject(res, UserInfo.class);
            return userInfo;
        }
        return null;
    }


    /**
     * 批量获取用户数据
     * @param openidList
     * @return
     * @throws Exception
     */
    public static List<UserInfo> getUserInfoBatch(List<String> openidList) throws Exception {
        AccessToken accessToken = WechatApiUtil.getAccessToken();
        if(accessToken != null){
            String url = Constant.get("batch_userinfo");
            List<Map<String, String>> userlist = new ArrayList<>();
            for(String openid: openidList){
                Map<String, String> map = new HashMap<>();
                map.put("openid", openid);
                map.put("lang", "zh_CN");
                userlist.add(map);
            }
            Map<String, Object> parambody = new HashMap<>();
            parambody.put("user_list", userlist);
            String res = HttpUtil.postJson(url, parambody);
            JSONObject obj = JSON.parseObject(res);
            if(res.contains("\"errcode\"")){
                logger.error("批量获取用户信息失败， {}", obj.get("errmsg"));
                return null;
            }else {
                JSONArray array = obj.getJSONArray("user_info_list");
                List<UserInfo> userInfoList = new ArrayList<>();
                for(int i = 0 ; i < array.size(); i++){
                    UserInfo userInfo = (UserInfo) array.get(i);
                    userInfoList.add(userInfo);
                }
                return userInfoList;
            }
        }
        return null;
    }


    /**
     * 获取微信服务器ip地址
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static String getWechatIp() throws IOException, GeneralSecurityException {
        String url = Constant.get("wechat_ip");
        AccessToken accessToken = WechatApiUtil.getAccessToken();
        Map<String, String> param = new HashMap<>();
        param.put("access_token", accessToken.getAccessToken());
        String res = HttpUtil.get(url, param);
        JSONObject obj = JSON.parseObject(res);
        if(obj.containsKey("errcode")){
            logger.error("获取微信服务器ip地址失败, {}", obj.get("errmsg"));
            return null;
        }else{
            JSONArray array = obj.getJSONArray("ip_list");
            return array.toJSONString();
        }
    }

    /**
     * 发送客服消息
     */
    public static void sendCustom(){
        String url = Constant.get("custom_send");
    }


    /**
     * 生成二维码
     * @throws Exception
     */
    public static void generateErcode() throws Exception {
        String url = Constant.get("ercode");
        AccessToken token = WechatApiUtil.getAccessToken();
        url +=  "?access_token=" + token.getAccessToken();
//        url += "?access_token=" + "zXcTJZHmxoEs_mNCsOyi1eFt3PHZ7dY7IKwdJcytVAWG-H37XnO2gv7h0K3g9MxfdT9uk_Yx2-92XsGcPIo2I8n5N6TXa-WxwdpBxMOCyrIv0KMVewuxYQ8r8MjnWTIQARPaAAAVMK";
//        {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
        Map<String, Object> param = new HashMap<>();
        param.put("expire_seconds", 60800);
        param.put("action_name", "QR_SCENE");
        Map<String, Object> map = new HashMap<>();
        map.put("scene_id", 123);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("scene", map);
        param.put("action_info", map1);
        String res = HttpUtil.postJson(url, param);
        JSONObject obj = JSON.parseObject(res);
        if(obj.containsKey("errcode")){
            logger.error("获取二维码ticket， {}", obj.get("errmsg"));
        }else{
            String ticket = obj.getString("ticket");
            url = Constant.get("ercode_img");
            Map<String, String> ticketParam = new HashMap<>();
            ticketParam.put("ticket", ticket);
            res = HttpUtil.get(url, ticketParam);
        }
    }


    /**
     * 长url转短url，提交二维码识别效率和识别率
     * @param longUrl
     * @return
     * @throws Exception
     */
    public static String qrCodeLongToShort(String longUrl) throws Exception {
        String url = Constant.get("long2short");
        Map<String, Object> param = new HashMap<>();
        AccessToken token = WechatApiUtil.getAccessToken();
        param.put("access_token", token.getAccessToken());
        param.put("action", "long2short");
        param.put("long_url", longUrl);
        String res = HttpUtil.postJson(url, param);
        JSONObject obj = JSON.parseObject(res);
        if(obj.getInteger("errcode") == 0){
            String shortUrl = obj.getString("short_url");
            return shortUrl;
        }else{
            logger.error("长url转短url失败 , {}", obj.get("errmsg"));
        }
        return null;
    }
}
