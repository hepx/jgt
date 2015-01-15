package org.hepx.jgt.ssj.web.interceptor;

import org.hepx.jgt.common.token.TokenHelper;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Token 验证拦截器
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
                //验证不通过返回403
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"Bad or missing Token value");
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
