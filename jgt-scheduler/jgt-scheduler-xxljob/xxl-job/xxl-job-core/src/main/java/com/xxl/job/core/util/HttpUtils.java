/*
 * Copyright (c) 2005-2018.  FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */

/**
 *
 */
package com.xxl.job.core.util;

import com.xxl.job.core.log.XxlJobLogger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * http tool
 *
 * @author hepx
 * @date 2020/10/27 17:34
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static HttpClient httpClient;
    private static PoolingHttpClientConnectionManager cm;
    private static RequestConfig requestConfig;

    //失败重连
    private static HttpRequestRetryHandler requestRetryHandler;

    static {
        cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // 检查永久连接可用性的间隔时间，单位ms
        cm.setValidateAfterInactivity(2000);
        // 设置超时30秒
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();

        requestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount,
                                        HttpContext context) {
                //失败重连大于3次就放弃
                if (executionCount > 5) {
                    logger.error("Maximum tries reached for client http pool");
                    return false;
                }
                if (exception instanceof org.apache.http.NoHttpResponseException ||
                        (exception instanceof SocketException && ("Connection reset").equals(exception.getMessage()))) {
                    XxlJobLogger.log("重试第" + executionCount + "次");
                    XxlJobLogger.log(exception);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        XxlJobLogger.log(e);
                    }
                    return true;
                }
                return false;
            }
        };

        httpClient = HttpClients.custom().setRetryHandler(requestRetryHandler).setConnectionManager(cm).setDefaultRequestConfig
                (requestConfig).build();

//        try {
//            sslHttpClient = getSSLHttpClient();
//        } catch (NoSuchAlgorithmException e) {
//            logger.error("init ssl httpclient error", e);
//        } catch (KeyManagementException e) {
//            logger.error("init ssl httpclient error", e);
//        }

    }

    /**
     * http client get request
     *
     * @param url
     * @return
     */
    public static String get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
//                logger.error("访问路径：{},响应：{}", url, response.getStatusLine().getStatusCode());
                throw new Exception(url + "响应结果：" + response.getStatusLine().getStatusCode());
            }
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * http client post request
     *
     * @param url
     * @param params
     * @param @see   org.springframework.http.MediaType
     * @return
     */
    public static String post(String url, Map<String, String> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity);
            } else {
                throw new Exception(url + "响应结果：" + response.getStatusLine().getStatusCode());
            }
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * post请求，body为json字符串
     *
     * @param url    请求路径
     * @param params 参数对象
     * @return
     */
    public static String postJson(String url, Object params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        if (params != null) {
            String paramStr = GsonTool.toJson(params);
            httpPost.setEntity(new StringEntity(paramStr, Charset.forName("UTF-8")));
        }
        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new Exception(url + "响应结果：" + response.getStatusLine().getStatusCode());
            }
        }

    }


    /**
     * 向请求添加头域
     *
     * @param request 请求对象
     * @param headers 头域map
     * @return 添加头域后的请求对象
     */
    private static HttpRequestBase setHeaders(HttpRequestBase request, Map<String, String> headers) {
        Iterator<String> keyIterator = headers.keySet().iterator();
        String key = null;
        while (keyIterator.hasNext()) {
            key = keyIterator.next();
            request.addHeader(key, headers.get(key));
        }
        return request;
    }

}
