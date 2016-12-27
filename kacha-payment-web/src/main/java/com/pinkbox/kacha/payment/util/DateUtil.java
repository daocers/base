package com.pinkbox.kacha.payment.util;

import java.util.Date;

/**
 * Created by daocers on 2016/9/28.
 */
public class DateUtil {
    /**
     * 获取时间戳，以秒为单位
     * @return
     */
    public static long getTimestampOfSecond(){
        return getTimestampOfMillSec() / 1000;
    }

    /**
     * 获取时间戳
     * 以毫秒为单位
     * @return
     */
    public static long getTimestampOfMillSec(){
        return new Date().getTime();
    }

    public static void main(String[] args){
        System.out.println(getTimestampOfMillSec());
        System.out.println(getTimestampOfSecond());
    }
}
