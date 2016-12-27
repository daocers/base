package co.bugu.pay.controller;

import co.bugu.pay.constant.AlipayConstant;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by daocers on 2016/9/22.
 */
@RequestMapping("/payment")
@Controller
public class AliPayWapController {
    private static Logger logger = LoggerFactory.getLogger(AliPayWapController.class);



    @RequestMapping("/pay")
    public String pay(String outTradeNo, Double totalAmount, String subject,
                      String body, String timeoutExpress,
                       HttpServletResponse response){
        try{
            String appId = AlipayConstant.get("app_id");
            String appPrivateKey = "";
            List<String> list  = IOUtils.readLines(AliPayWapController.class.
                    getClassLoader().getResourceAsStream("conf/pem/rsa_private_key_pkcs8.pem"), "utf-8");
            StringBuilder builder = new StringBuilder();
            int lineNum = 0;
            for(String line: list){
                lineNum++;
                if(lineNum == 1 || lineNum == list.size()){
                    continue;
                }
                builder.append(line)
                        .append("\n");
            }
            appPrivateKey = builder.toString();

            String charset = AlipayConstant.get("charset");
            String aliPublicKey = AlipayConstant.get("alipay_public_key");
            String alipayGateway = AlipayConstant.get("alipay_gateway");
            String sellerId = AlipayConstant.get("seller_id");

            AlipayClient alipayClient = new DefaultAlipayClient(alipayGateway,
                            appId, appPrivateKey, "json", charset, aliPublicKey);

            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setReturnUrl(AlipayConstant.get("return_url"));
            request.setNotifyUrl(AlipayConstant.get("notify_url"));
            request.setApiVersion("1.0");

            Map<String, Object> content = new HashMap<>();
            content.put("out_trade_no", outTradeNo);
            content.put("total_amount", totalAmount);
            content.put("subject", subject);
            content.put("seller_id", sellerId);
            content.put("timeout_express", timeoutExpress);
            content.put("product_code", "QUICK_WAP_PAY");
            request.setBizContent(JSON.toJSONString(content));
            String form = alipayClient.pageExecute(request).getBody();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(form);
            response.getWriter().flush();

        }catch (Exception e){
            logger.error("支付失败", e);
        }
        return null;
    }

    /**
     * 支付宝通知
     * @param request
     * @return
     */
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request){
        try{
            Map<String, String> params = request.getParameterMap();

        }catch (Exception e){
            logger.error("处理通知信息失败", e);
        }
        return "index";
    }

    @RequestMapping("/return")
    public String returnUrl(HttpServletRequest request){
        try{
//            String content = IOUtils.toString(request.getInputStream(), "utf-8");
        }catch (Exception e){
            logger.error("处理通知信息失败", e);
        }
        return null;
    }
}
