package org.hepx.jgt.common.ajax;

import javax.servlet.http.HttpServletRequest;

/**
 * ajax 工具类
 *
 * @author: Koala
 * @Date: 14-8-21 下午6:32
 * @Version: 1.0
 */
public class AjaxUtil {

    /**
     * 判断是否为AJAX请求
     *
     * @param webRequest
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest webRequest) {
        return (webRequest.getHeader("accept").indexOf("application/json") != -1 || (webRequest
                .getHeader("X-Requested-With") != null && webRequest.getHeader("X-Requested-With")
                .indexOf("XMLHttpRequest") != -1));
    }

    /**
     * 判断是否为AJAX上传
     *
     * @param webRequest
     * @return
     */
    public static boolean isAjaxUploadRequest(HttpServletRequest webRequest) {
        return webRequest.getParameter("ajaxUpload") != null;
    }

}
