package org.hepx.jgt.common.http;

import org.hepx.jgt.common.JgtConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * HTTP 工具类
 *
 * @author: Koala
 * @Date: 14-8-23 下午11:10
 * @Version: 1.0
 */
public class HttpUtil {


    /**
     * 获取URL查询条件
     * @param request
     * @param encode 编码
     * @return
     * @throws IOException
     */
    public static String getQueryString(HttpServletRequest request, String encode) throws IOException {
        StringBuffer sb = request.getRequestURL();
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            sb.append("?").append(query);
        }
        return URLEncoder.encode(sb.toString(), encode);
    }

    /**
     * getRequestURL是否包含在URL之内
     * @param request
     * @param url     参数为以';'分割的URL字符串
     * @return
     */
    public static boolean inContainURL(HttpServletRequest request, String url) {
        boolean result = false;
        if (url != null && !"".equals(url.trim())) {
            String[] urlArr = url.split(";");
            StringBuffer reqUrl = request.getRequestURL();
            for (int i = 0; i < urlArr.length; i++) {
                if (reqUrl.indexOf(urlArr[i]) > 1) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * URLEncoder返回地址
     * @param url      跳转地址
     * @param retParam 返回地址参数名
     * @param retUrl   返回地址
     * @return
     */
    public static String encodeRetURL(String url, String retParam, String retUrl) {
        if (url == null) {
            return null;
        }
        StringBuffer retStr = new StringBuffer(url);
        retStr.append("?");
        retStr.append(retParam);
        retStr.append("=");
        try {
            retStr.append(URLEncoder.encode(retUrl, JgtConstant.ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retStr.toString();
    }

    /**
     * GET 请求
     * <p/>
     *
     * @param request
     * @return boolean
     */
    public boolean isGet(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        return false;
    }

    /**
     * POST 请求
     * <p/>
     *
     * @param request
     * @return boolean
     */
    public boolean isPost(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        return false;
    }
}
