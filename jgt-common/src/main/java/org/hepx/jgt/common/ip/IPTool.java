package org.hepx.jgt.common.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP地址工具类
 * @author: Koala
 * @Date: 14-8-21 下午6:18
 * @Version: 1.0
 */
public class IPTool {

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
