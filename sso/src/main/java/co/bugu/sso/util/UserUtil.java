package co.bugu.sso.util;

import co.bugu.framework.util.EncryptUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daocers on 2016/6/28.
 */
public class UserUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取登录后的ticket
     * @param username
     * @return
     */
    public static String getTicket(String username){
        Date now = new Date();
        username += format.format(now);
        return EncryptUtil.md5(username);
    }

}
