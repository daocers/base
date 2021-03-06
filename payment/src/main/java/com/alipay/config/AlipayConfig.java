package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088021069278584";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOxexd31pbSircluMt/"
			+ "DY+g8hSzr8iKVZV02qXvnA9CZWQ3CiORM8bqTGzJN3hfm+5kNeywpfc+RRzT3N6Ez94Dz82sX5i/NckEkxrnufjolledoXsMxKvQdzcLzhU"
			+ "iOhehWhpSG0nEEmm3Y1TvA1LJJwJkHXaqA7cgOHt6U5rv3AgMBAAECgYEApFZ8IYcRz5YvV9XrPxH9G1FAkmv1LEBMelNv4LpMN7L2bI+tV3"
			+ "V7t2lhxkbHPD0W/mGWGq06UJ9EL7oWDlkwITzn7GikBPGreDOJk9qQ+fOqN0GfaJDAaIpGv2qYQ4KAzUF36SVflxobup5CQrDTacTMkJDvLAR"
			+ "uM+8SOXumF6kCQQD4kqmHDVWT2havHxpHGOtFX9RTx8VEbjihKl3irA70oMD0FTmCsmQHt2i8ty0lrgqG7vgXLlchHHGXI6r2z2j7AkEA827Fq"
			+ "f4NyUrkTxG35AB2I4vSe0iqW0dDJ6Xc64tkuSjUBJquPqW7JDXnQnabrTmB1JIq2VeWR6EnGy6j3gIANQJBAIyDo6+DWyf8NayDAYDFVmHeHRHL"
			+ "MPlQ8VQxtebn6oBgyxJvWRZHr4IpLNzZE31kV/EU70tzV6+Q+6k7cW6ZqrcCQC5GPljkLGa5T9EgAx7aX5q+N5Kr64ZSc5eT1f0IkGsKMdN2O+04"
			+ "v+xQOtfNiCZTqjRoXLDYD38qSS7HnOrU4okCQQCDISbhzW3WHwO84FdftB6zBJ8gXKieIV5uw7ACqq9WgL7yS6+j4hKi/ZE7VjSqaUkx6vuV4vNm/ocSwj1PYXxw";


	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";


	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://520622a1.nat123.net/payment/notify.do";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.baidu.com";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "UTF-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

