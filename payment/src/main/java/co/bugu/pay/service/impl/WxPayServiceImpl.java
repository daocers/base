package co.bugu.pay.service.impl;

import co.bugu.framework.core.util.HttpUtil;
import co.bugu.framework.util.XmlUtil;
import co.bugu.pay.constant.PaymentConfig;
import co.bugu.pay.domain.wechat.*;
import co.bugu.pay.service.IWxPayService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;

/**
 * Created by daocers on 2016/9/23.
 */
@Service
public class WxPayServiceImpl implements IWxPayService {
    @Override
    public UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException {
        String url = PaymentConfig.getWx("url_unified_order");
        return processRequest(url, request, UnifiedOrderResponse.class);
    }

    @Override
    public QueryResponse queryOrder(QueryRequest queryRequest) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException {
        String url = PaymentConfig.getWx("url_query_order");
        return processRequest(url, queryRequest, QueryResponse.class);
    }

    @Override
    public CloseOrderResponse closeOrder(CloseOrderRequest closeOrderRequest) throws NoSuchMethodException, GeneralSecurityException, IllegalAccessException, IOException, InvocationTargetException, ClassNotFoundException {
        String url = PaymentConfig.getWx("url_close_order");
        return processRequest(url, closeOrderRequest, CloseOrderResponse.class);
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) throws NoSuchMethodException, GeneralSecurityException, IllegalAccessException, IOException, InvocationTargetException, ClassNotFoundException {
        String url = PaymentConfig.getWx("url_refund_order");
        return processRequest(url, refundRequest, RefundResponse.class);
    }

    private <T> T processRequest(String url, Object request, Class<T> tClass) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IOException, GeneralSecurityException {
        String xml = XmlUtil.objToXml(request, XmlUtil.FIELD_POLICY_UNDERLINE);
        String res = HttpUtil.postXml(url, xml, HttpUtil.UTF8, HttpUtil.connTimeout, HttpUtil.readTimeout);
        T obj = XmlUtil.xmlToObj(res, tClass, XmlUtil.FIELD_POLICY_UNDERLINE);
        return obj;
    }
}
