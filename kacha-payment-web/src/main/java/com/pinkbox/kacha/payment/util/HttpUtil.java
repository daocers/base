package com.pinkbox.kacha.payment.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http调用工具类
 * Created by daocers on 2016/6/14.
 */
public class HttpUtil {
    public static final int connTimeout = 5000;
    public static final int readTimeout = 5000;
    public static final String mimeType_form = "application/x-www-form-urlencoded";
    public static final String mimeType_xml = "application/xml";
    public static final String mimeType_json = "application/json";

    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);


    public static String postJson(String url, Map<String, Object> params) throws Exception {
        return processPost(url, params, UTF8, mimeType_json, connTimeout, readTimeout);
    }

    public static String postForm(String url, Map<String, Object> params) throws Exception {
        return processPost(url, params, UTF8, mimeType_form, connTimeout, readTimeout);
    }

//    public static String postXml(String url, Map<String, Object> params) throws Exception {
//        return processPost(url, params, UTF8, mimeType, connTimeout, readTimeout);
//    }

    public static String get(String url, Map<String, String> params) throws IOException, GeneralSecurityException {
        return processGet(url, params, UTF8, connTimeout, readTimeout);
    }

    public static String get(String url, Map<String, String> params, String charset, Integer connTimeout, Integer readTimeout) throws IOException, GeneralSecurityException {
        return processGet(url, params, charset, connTimeout, readTimeout);
    }


    public static void main(String[] args){
        String customer = "http://poll.kuaidi100.com/poll/query.do";
        String sign = "";
        Map<String, String> param = new HashMap<>();
        param.put("com", "shentong");
        param.put("num", "");
        param.put("from", "");
        param.put("to", "");
//        HttpUtil.post()
    }

    /**
     * 处理post提交，支持类型为form和json
     *
     * @param url
     * @param params
     * @param mimeType
     * @param charset
     * @param connTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    private static String processPost(String url, Map<String, Object> params, String charset, String mimeType,
                                      Integer connTimeout, Integer readTimeout) throws Exception {
        long startTime = System.currentTimeMillis();
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "";
        HttpEntity entity = null;
        try {
            if (mimeType.equals(mimeType_form)) {//form提交
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
                }
                entity = new UrlEncodedFormEntity(nameValuePairs, charset);
            } else if (mimeType.equals(mimeType_json)) {//json提交
                entity = new StringEntity(JSON.toJSONString(params), ContentType.create(mimeType, charset));
            } else {//其他提交
                logger.error("尚未支持的mimeType");
                throw new Exception("尚未支持的mimeType");
            }
            post.setEntity(entity);
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setConfig(customReqConf.build());

            HttpResponse res;
            if (url.startsWith("https")) {
                client = createSSLInsecureClient();
            } else {
                client = createDefaultClient();
            }
            res = client.execute(post);
            result = IOUtils.toString(res.getEntity().getContent(), charset);

            long endTime = System.currentTimeMillis();
            logger.info("HttpUtil Method:[post] ,URL[" + url + "] ,Time:[" + (endTime - startTime) + "ms] ,result:" + result);
        } catch (ConnectTimeoutException ex) {
            throw ex;
        } catch (SocketTimeoutException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * post 提交 xml数据
     *
     * @param url
     * @param body
     * @param charset
     * @param connTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static String postXml(String url, String body, String charset, Integer connTimeout, Integer readTimeout) throws IOException, GeneralSecurityException {
        long startTime = System.currentTimeMillis();
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "";
        HttpEntity entity = null;
        try {
            entity = new StringEntity(body, ContentType.create(mimeType_xml, charset));
            post.setEntity(entity);
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setConfig(customReqConf.build());

            HttpResponse res;
            if (url.startsWith("https")) {
                client = createSSLInsecureClient();
            } else {
                client = createDefaultClient();
            }
            res = client.execute(post);
            result = IOUtils.toString(res.getEntity().getContent(), charset);

            long endTime = System.currentTimeMillis();
            logger.info("HttpUtil Method:[post] ,URL[" + url + "] ,Time:[" + (endTime - startTime) + "ms] ,result:" + result);
        } catch (ConnectTimeoutException ex) {
            throw ex;
        } catch (SocketTimeoutException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 处理get请求
     *
     * @param url
     * @param params
     * @param charset
     * @param connTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    private static String processGet(String url, Map<String, String> params, String charset, Integer connTimeout, Integer readTimeout) throws IOException, GeneralSecurityException {
        if (params != null && params.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buffer.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            if (buffer.length() > 0) {
                url = url + "?" + buffer.substring(0, buffer.length() - 1);
            }
        }

        long startTime = System.currentTimeMillis();
        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result = "";
        try {
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            get.setConfig(customReqConf.build());

            HttpResponse res = null;

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
            } else {
                // 执行 Http 请求.
                client = createDefaultClient();
            }
            res = client.execute(get);
            result = IOUtils.toString(res.getEntity().getContent(), charset);

            long endTime = System.currentTimeMillis();
            logger.info("HttpUtil Method:[postForm] ,URL[" + url + "] ,Time:[ " + (endTime - startTime) + "ms ] ,result:" + result);
        } catch (ConnectTimeoutException ex) {
            throw ex;
        } catch (SocketTimeoutException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        } finally {
            get.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;

    }


    /**
     * 创建连接
     *
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createDefaultClient() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        return httpclient;
    }

    /**
     * 创建 SSL连接
     *
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }

            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }


}
