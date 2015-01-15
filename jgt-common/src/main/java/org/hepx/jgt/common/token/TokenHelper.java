package org.hepx.jgt.common.token;

import org.hepx.jgt.common.random.RandomGenerater;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Token辅助类
 * User: hepanxi
 * Date: 15-1-14
 * Time: 上午11:30
 */
public class TokenHelper {

    //生成hideen的名字
    public static final String INPUT_TOKEN_NAME="jgt_token";

    /**
     * 生成UUID token
     * @return
     */
    public static String generateToken(HttpSession session){
        String token = null;
        synchronized (session) {
            //避免多选项卡操作其它token失效
            token = (String) session.getAttribute(INPUT_TOKEN_NAME);
            if (null == token) {
                token =  RandomGenerater.generate32UUID();
                session.setAttribute(INPUT_TOKEN_NAME, token);
            }
        }
        return token;

    }

    /**
     * 添加token到Session中
     * @param request
     * @param token
     */
    public static void addToken(HttpServletRequest request,String token){
        HttpSession session = request.getSession();
        session.setAttribute(INPUT_TOKEN_NAME,token);
    }

    /**
     * 从session中移除token
     * @param request
     * @param token
     */
    public static void removeToken(HttpServletRequest request,String token){
        HttpSession session = request.getSession();
        session.setAttribute(INPUT_TOKEN_NAME,null);//先将值设为空，再移除
        session.removeAttribute(token);
    }

    /**
     * 验证两个token是否一至
     * @param request
     * @param token
     * @return
     */
    public static boolean verifyToken(HttpServletRequest request,String token){
        HttpSession session = request.getSession();
        //原始TOKEN
        String o_token = (String)session.getAttribute(INPUT_TOKEN_NAME);
        if(o_token != null && o_token.equals(token)){
            //token只能保证使用一次。验证过的token将失效
            removeToken(request,token);
            return true; //验证成功
        }else{
            return false;//验证失败
        }
    }

    /**
     * 从request中取得token
     * @param request
     * @return
     */
    public static String getTokenForRequest(HttpServletRequest request){
        return request.getParameter(INPUT_TOKEN_NAME);
    }
}
