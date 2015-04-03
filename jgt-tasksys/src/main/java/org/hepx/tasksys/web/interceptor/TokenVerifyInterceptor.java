package org.hepx.tasksys.web.interceptor;

import org.hepx.jgt.common.ajax.AjaxUtil;
import org.hepx.jgt.common.token.TokenHelper;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Token 验证拦截器
 * 说明：Token拦截器拦截所有POST请求。
 * 1:form提交的请求，token做为一个hidden隐藏域参数同其它参数一起传递到后台，后台通过request.getParameter()来取token
 * 2:ajax提交的请求，token需要放置在header中做为头信息的一参数传递后台，后台通过request.getHeader()来取token
 * User: hepanxi
 * Date: 15-1-14
 * Time: 下午5:42
 */
public class TokenVerifyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //处理所有POST请求
        if(request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())){
            String token = TokenHelper.getTokenForRequest(request);
            if(TokenHelper.verifyToken(request,token)){
                return true;
            }else{
                //验证不通过,返回403
                if(AjaxUtil.isAjaxRequest(request)){
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().println("Bad or missing Token value");
                }else{
                    response.sendError(HttpServletResponse.SC_FORBIDDEN,"Bad or missing Token value");
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 检查请求参数中是否包含token验证字段
     * @param request
     * @return
     */
    private boolean isTokenPage(HttpServletRequest request){
        Enumeration<String>fields = request.getParameterNames();
        while (fields!=null && fields.hasMoreElements()){
            String name = fields.nextElement();
            if(TokenHelper.INPUT_TOKEN_NAME.equals(name)){
                return true;
            }
        }
        return false;
    }
}
