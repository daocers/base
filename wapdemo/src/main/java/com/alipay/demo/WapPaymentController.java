package com.alipay.demo;

import com.alipay.AlipayConstant;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.alipay.sdk.wappay.AlipayConfigs;
import com.alipay.sdk.wappay.model.builder.AlipayWapPayRequestBuilder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by daocers on 2016/9/24.
 */
@Controller
@RequestMapping("/wappay")
public class WapPaymentController {
    private static Logger logger = LoggerFactory.getLogger(WapPaymentController.class);

    @RequestMapping("/test")
    public String test(HttpServletResponse response) {
        try {

            String ALIPAY_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOxexd31pbSircluMt/"
                    + "DY+g8hSzr8iKVZV02qXvnA9CZWQ3CiORM8bqTGzJN3hfm+5kNeywpfc+RRzT3N6Ez94Dz82sX5i/NckEkxrnufjolledoXsMxKvQdzcLzhU"
                    + "iOhehWhpSG0nEEmm3Y1TvA1LJJwJkHXaqA7cgOHt6U5rv3AgMBAAECgYEApFZ8IYcRz5YvV9XrPxH9G1FAkmv1LEBMelNv4LpMN7L2bI+tV3"
                    + "V7t2lhxkbHPD0W/mGWGq06UJ9EL7oWDlkwITzn7GikBPGreDOJk9qQ+fOqN0GfaJDAaIpGv2qYQ4KAzUF36SVflxobup5CQrDTacTMkJDvLAR"
                    + "uM+8SOXumF6kCQQD4kqmHDVWT2havHxpHGOtFX9RTx8VEbjihKl3irA70oMD0FTmCsmQHt2i8ty0lrgqG7vgXLlchHHGXI6r2z2j7AkEA827Fq"
                    + "f4NyUrkTxG35AB2I4vSe0iqW0dDJ6Xc64tkuSjUBJquPqW7JDXnQnabrTmB1JIq2VeWR6EnGy6j3gIANQJBAIyDo6+DWyf8NayDAYDFVmHeHRHL"
                    + "MPlQ8VQxtebn6oBgyxJvWRZHr4IpLNzZE31kV/EU70tzV6+Q+6k7cW6ZqrcCQC5GPljkLGa5T9EgAx7aX5q+N5Kr64ZSc5eT1f0IkGsKMdN2O+04"
                    + "v+xQOtfNiCZTqjRoXLDYD38qSS7HnOrU4okCQQCDISbhzW3WHwO84FdftB6zBJ8gXKieIV5uw7ACqq9WgL7yS6+j4hKi/ZE7VjSqaUkx6vuV4vNm/ocSwj1PYXxw";


            AlipayConfigs configs = new AlipayConfigs();
            String appId = AlipayConstant.get("app_id");
            String appPrivateKey = "";
            List<String> list = IOUtils.readLines(WapPaymentController.class.
                    getClassLoader().getResourceAsStream("conf/pem/rsa_private_key_pkcs8.pem"), "utf-8");
            StringBuilder builder = new StringBuilder();
            int lineNum = 0;
            for (String line : list) {
                lineNum++;
                if (lineNum == 1 || lineNum == list.size()) {
                    continue;
                }
                builder.append(line).append("\n");
            }
            appPrivateKey = builder.toString();
            appPrivateKey = ALIPAY_PRIVATE_KEY;
            String charset = AlipayConstant.get("charset");
            String aliPublicKey = AlipayConstant.get("alipay_public_key");
            String alipayGateway = AlipayConstant.get("alipay_gateway");
            String sellerId = AlipayConstant.get("seller_id");
            String returnUrl = AlipayConstant.get("return_url");
            String notifyUrl = AlipayConstant.get("notify_url");

            AlipayWapPayRequestBuilder builder1 = new AlipayWapPayRequestBuilder();
            builder1.setOutTradeNo("12312312312")
                    .setSubject("iphone700")
                    .setTotalAmount("0.01")
                    .setBody("打折iphone")
                    .setTimeoutExpress("10h")
                    .setReturnUrl(returnUrl)
                    .setNotifyUrl(notifyUrl)
                    .setSellerId(sellerId);

            AlipayClient alipayClient = new DefaultAlipayClient(alipayGateway,
                    appId, appPrivateKey, "json", charset, aliPublicKey);
            AlipayTradeWapPayResponse response1 = alipayClient.pageExecute(builder1.getRequest());
            String url = null;
            if (response1 != null) {
                url = response1.getBody();
            }
//
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(url);
            response.getWriter().flush();
        } catch (Exception e) {
            logger.error("支付失败", e);
        }
        return null;
    }
}
