package co.bugu.pay.controller;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daocers on 2016/9/28.
 */
@Controller
@RequestMapping("/wap")
public class Alipay1_0_Controller {
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(HttpServletRequest request,
            @RequestParam(required = false, value = "WIDout_trade_no") String outTradeNo,
                      @RequestParam(required = false, value = "WIDsubject") String subject,
                      @RequestParam(required = false, value = "WIDtotal_fee") String totalFee,
                      @RequestParam(required = false, value = "WIDshow_url") String showUrl,
                      @RequestParam(required = false, value = "WIDbody") String body,
                      HttpServletResponse response) throws IOException {

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("out_trade_no", outTradeNo);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", totalFee);
        sParaTemp.put("show_url", showUrl);
        sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
        sParaTemp.put("body", body);
        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
        //如sParaTemp.put("参数名","参数值");


        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(sHtmlText);
        response.getWriter().flush();
//        out.println(sHtmlText);
//        %>
        return null;
    }
}
