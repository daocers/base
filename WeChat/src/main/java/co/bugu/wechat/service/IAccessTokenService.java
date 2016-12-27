package co.bugu.wechat.service;

import co.bugu.wechat.model.common.AccessToken;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by daocers on 2016/9/13.
 */
public interface IAccessTokenService {
    String getAccessToken(String appid) throws IOException, GeneralSecurityException;
}
