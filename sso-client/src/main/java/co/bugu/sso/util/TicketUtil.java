package co.bugu.sso.util;

import co.bugu.sso.constant.GlobalConstant;

/**
 * Created by daocers on 2016/6/28.
 */
public class TicketUtil {

    /**
     * 校验ticket是否可用
     * @param ticket
     * @return
     */
    public static boolean checkTicket(String ticket) throws Exception {
        if("".equals(ticket)){
            return false;
        }else{
            return JedisUtil.isExist(ticket);
        }
    }

    /**
     * 更新ticket实效
     * @param ticket
     */
    public static void updateTicket(String ticket){
        JedisUtil.expire(ticket, GlobalConstant.getExpireTime());
    }

    /**
     * 根据ticket获取用户id
     * @param ticket
     * @return
     */
    public static Integer getUserIdByTicket(String ticket){
        String val = JedisUtil.get(ticket);
        if(val == null || "".equals(val)){
            return null;
        }else{
            return Integer.valueOf(val);
        }
    }

}
