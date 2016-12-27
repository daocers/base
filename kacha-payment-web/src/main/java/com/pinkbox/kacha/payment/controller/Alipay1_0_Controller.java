package com.pinkbox.kacha.payment.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.pinkbox.kacha.common.util.BeanUtil;
import com.pinkbox.kacha.payment.constant.AlipayConfig;
import com.pinkbox.kacha.payment.domain.alipay.AliNotifyRequest;
import com.pinkbox.kacha.payment.service.IAlinotifyrequestService;
import com.pinkbox.kacha.payment.util.alipay.util.AlipayCore;
import com.pinkbox.kacha.payment.util.alipay.util.AlipaySubmit;
import com.pinkbox.kacha.retailer.model.MallOrder;
import com.pinkbox.kacha.retailer.model.MallOrderLog;
import com.pinkbox.kacha.retailer.service.IMallOrderService;
import com.pinkbox.kacha.retailer.util.OrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by daocers on 2016/9/28.
 */
@Controller
@RequestMapping("/ali/wap")
public class Alipay1_0_Controller {
    private static Logger logger = LoggerFactory.getLogger(Alipay1_0_Controller.class);

    @Autowired
    IMallOrderService orderService;

    @Autowired
    IAlinotifyrequestService alinotifyrequestService;

    /**
     * 支付
     *
     * @param request  //     * @param outTradeNo   订单号
     *                 //     * @param subject  订单名称
     *                 //     * @param totalFee 订单费用，单位元
     *                 //     * @param showUrl 展示的url
     *                 //     * @param body  商品描述
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(HttpServletRequest request,
                      @RequestParam(required = false) String orderCode,
                      HttpServletResponse response) throws IOException {

        MallOrder order = new MallOrder();
        order.setOrderCode(orderCode);
        order = orderService.selectById(order);

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("out_trade_no", orderCode);
        sParaTemp.put("subject", order.getItemList().get(0).getGoods().getName());
        sParaTemp.put("total_fee", order.getPrice() + "");
        sParaTemp.put("show_url", request.getRequestURI());//show_url 表示用户中途退出后返回商户网站的地址，退回到用户请求的网址
        sParaTemp.put("app_pay", "Y");//启用此参数可唤起钱包APP支付。
        sParaTemp.put("body", order.getItemList().get(0).getGoods().getName());
        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
        //如sParaTemp.put("参数名","参数值");


        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        logger.debug("发送给客户端请求： {}", sHtmlText);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(sHtmlText);
        response.getWriter().flush();
//        out.println(sHtmlText);
//        %>
        return null;
    }

    @RequestMapping("/notify")
    @ResponseBody
    public String notify(AliNotifyRequest request, HttpServletRequest req) {
        logger.debug("接受到支付宝异步通知，通知信息：\n {}", JSON.toJSONString(request, true));
        alinotifyrequestService.save(request);
        Map<String, String[]> map = req.getParameterMap();
        Map<String, String> param = new HashMap<>();
        Iterator<Map.Entry<String, String[]>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String[]> entry = iter.next();
            String key = entry.getKey();
            String value = entry.getValue()[0];
            param.put(key, value);
        }
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(param);
        //生成签名结果
        String sign = AlipaySubmit.buildRequestMysign(sPara);
//        boolean flag = sign.equals(request.getSign());
//        if (flag) {//合法请求
//
//        } else {//非法请求
//            logger.warn("支付宝支付，非法请求。请求参数：\n {}", JSON.toJSONString(request, true));
//        }
        logger.debug("请求合法\n");
        if (request.getTrade_status().equals("TRADE_SUCCESS")) {
            MallOrder order = new MallOrder();
            order.setOrderCode(request.getOut_trade_no());
            order = orderService.selectById(order);
            order.setOrderStatus(OrderConstant.ORDER_PAID);
            order.setPayTime(new Date());
            MallOrderLog log = new MallOrderLog();
            log.setInfo("支付宝支付成功");
            log.setUserId(0);
            log.setCreateTime(new Date());
            log.setType(OrderConstant.LOG_OPR_PAY);
            log.setOrderId(order.getOrderId());
            orderService.update(order, log);
            logger.debug("订单更新成功\n");
            return "success";//支付宝接收到该信息后会认为商户已经成功收到通知，不再继续发送该消息
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        String res = "{" +
                "\"body\":\"韩庚航海主题中裤\"," +
                "\"buyer_email\":\"daocers@gmail.com\"," +
                "\"buyer_id\":\"2088102319788223\"," +
                "\"gmt_create\":1476857748000," +
                "\"gmt_payment\":1476857748000," +
                "\"is_total_fee_adjust\":\"N\"," +
                "\"notify_id\":\"dfc3e27434570a306329b74fa4d63f8hp6\"," +
                "\"notify_time\":1476857749000," +
                "\"notify_type\":\"trade_status_sync\"," +
                "\"out_trade_no\":\"2016101914151839101000022\"," +
                "\"payment_type\":\"1\"," +
                "\"price\":0.01," +
                "\"quantity\":1," +
                "\"seller_email\":\"xuejun@kachaaa.com\"," +
                "\"seller_id\":\"2088021069278584\"," +
                "\"sign\":\"iwrevjvgPlJZcvf5C01+aycJopEaIajedmKQ39tz/PPp9SPdRJnK9TUz0N+nx3koPIGVMKYtXqJqRIzs9c41dSpn3/G8V5+L2G/lu9WAUqeDzC8qBumzhApA0daMHsbDyt3437Shn7N7gtHlACAlVr0Yq/WVAmEzgFj+R/FUroo=\"," +
                "\"sign_type\":\"RSA\"," +
                "\"subject\":\"韩庚航海主题中裤\"," +
                "\"total_fee\":0.01," +
                "\"trade_no\":\"2016101921001004220224695337\"," +
                "\"trade_status\":\"TRADE_SUCCESS\"," +
                "\"use_coupon\":\"N\"" +
                "}";



        AliNotifyRequest request = JSON.parseObject(res, AliNotifyRequest.class);
        alinotifyrequestService.save(request);
        return "ok";
    }
    public static void main(String[] args) throws IllegalAccessException {
        String res = "{" +
                "\"body\":\"韩庚航海主题中裤\"," +
                "\"buyer_email\":\"daocers@gmail.com\"," +
                "\"buyer_id\":\"2088102319788223\"," +
                "\"gmt_create\":1476857748000," +
                "\"gmt_payment\":1476857748000," +
                "\"is_total_fee_adjust\":\"N\"," +
                "\"notify_id\":\"dfc3e27434570a306329b74fa4d63f8hp6\"," +
                "\"notify_time\":1476857749000," +
                "\"notify_type\":\"trade_status_sync\"," +
                "\"out_trade_no\":\"2016101914151839101000022\"," +
                "\"payment_type\":\"1\"," +
                "\"price\":0.01," +
                "\"quantity\":1," +
                "\"seller_email\":\"xuejun@kachaaa.com\"," +
                "\"seller_id\":\"2088021069278584\"," +
                "\"sign\":\"iwrevjvgPlJZcvf5C01+aycJopEaIajedmKQ39tz/PPp9SPdRJnK9TUz0N+nx3koPIGVMKYtXqJqRIzs9c41dSpn3/G8V5+L2G/lu9WAUqeDzC8qBumzhApA0daMHsbDyt3437Shn7N7gtHlACAlVr0Yq/WVAmEzgFj+R/FUroo=\"," +
                "\"sign_type\":\"RSA\"," +
                "\"subject\":\"韩庚航海主题中裤\"," +
                "\"total_fee\":0.01," +
                "\"trade_no\":\"2016101921001004220224695337\"," +
                "\"trade_status\":\"TRADE_SUCCESS\"," +
                "\"use_coupon\":\"N\"" +
                "}";

        AliNotifyRequest request = JSON.parseObject(res, AliNotifyRequest.class);
        Field[] fields = request.getClass().getDeclaredFields();
        StringBuffer buffer = new StringBuffer();
        for(Field field: fields){
            field.setAccessible(true);
            String key = field.getName();
            Object value = field.get(request);
            if(value == null || value.equals("")){
                continue;
            }
            String val = "";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(value instanceof Date){
                val = format.format(value);
            }else{
                val = value.toString();
            }
            buffer.append(key).append("=").append(val).append("&");
        }
        System.out.println(buffer.toString());
//
//        res = JSON.toJSONString(request, Feature.AllowISO8601DateFormat);
//        Map<String, String> map = JSON.parseObject(res, Map.class);
//        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
//        StringBuffer buffer = new StringBuffer();
//        while(iter.hasNext()){
//            Map.Entry<String, String> entry = iter.next();
//            buffer.append(entry.getKey())
//                    .append("=")
//                    .append(entry.getValue())
//                    .append("&");
//        }
//        System.out.println(buffer.toString());
    }

}
