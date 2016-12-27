package com.pinkbox.kacha.payment.service;


import com.pinkbox.kacha.payment.domain.wechat.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;

/**
 * 微信统一下单
 * Created by daocers on 2016/9/23.
 */
public interface IWxPayService {
    /**
     * 统一下单
     *
     * @param request
     * @return
     */
    UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException;

    /**
     * 查询订单
     *
     * @param queryRequest
     * @return
     */
    QueryResponse queryOrder(QueryRequest queryRequest) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException;

    /**
     * 关闭订单
     *
     * @param closeOrderRequest
     * @return
     */
    CloseOrderResponse closeOrder(CloseOrderRequest closeOrderRequest) throws NoSuchMethodException, GeneralSecurityException, IllegalAccessException, IOException, InvocationTargetException, ClassNotFoundException;

    /**
     * 退款
     *
     * @param refundRequest
     * @return
     */
    RefundResponse refund(RefundRequest refundRequest) throws NoSuchMethodException, GeneralSecurityException, IllegalAccessException, IOException, InvocationTargetException, ClassNotFoundException;

}
