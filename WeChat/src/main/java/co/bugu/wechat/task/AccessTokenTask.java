package co.bugu.wechat.task;

import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.util.JedisUtil;
import co.bugu.wechat.global.Constant;
import co.bugu.wechat.model.common.AccessToken;
import co.bugu.wechat.util.WechatApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

/**
 * Created by daocers on 2016/7/28.
 */
public class AccessTokenTask {
    @Autowired
    BaseDao baseDao;
    private static Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);

    public void getAccessToken() throws IOException, GeneralSecurityException {
        logger.debug("开始获取token， 当前时间：{}", new Date());
        String key = Constant.get("redis_access_token") + Constant.get("appid");
        AccessToken token = WechatApiUtil.getAccessToken();

        AccessToken tokenOld = baseDao.selectOne("wechat.accesstoken.selectById", token.getAppid());
        if(tokenOld != null){
            baseDao.update("wechat.accesstoken.updateById", token);
        }else{
            baseDao.insert("wechat.accesstoken.insert", token);
        }
        JedisUtil.set(key, token.getAccessToken());
        JedisUtil.expire(key, token.getExpiresIn());
        logger.debug("成功获取token， 当前时间{}", new Date());
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        AccessTokenTask task = new AccessTokenTask();
        task.getAccessToken();
    }
}
