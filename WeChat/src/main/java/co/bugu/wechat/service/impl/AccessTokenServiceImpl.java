package co.bugu.wechat.service.impl;

import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.util.JedisUtil;
import co.bugu.wechat.global.Constant;
import co.bugu.wechat.model.common.AccessToken;
import co.bugu.wechat.service.IAccessTokenService;
import co.bugu.wechat.util.WechatApiUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by daocers on 2016/9/13.
 */
@Service
public class AccessTokenServiceImpl implements IAccessTokenService {
    @Autowired
    BaseDao baseDao;
    @Override
    public String getAccessToken(String appid) throws IOException, GeneralSecurityException {
        String key = Constant.get("redis_access_token") + Constant.get("appid");
        String accessToken = JedisUtil.get(key);
        if(StringUtils.isNotBlank(accessToken)){
            return accessToken;
        }else{
            AccessToken token = baseDao.selectOne("wechat.accesstoken.selectById");
            if(token != null){
                JedisUtil.set(key, token.getAccessToken());
                JedisUtil.expire(key, token.getExpiresIn());
                return token.getAccessToken();
            }
        }
        return "";
    }
}
