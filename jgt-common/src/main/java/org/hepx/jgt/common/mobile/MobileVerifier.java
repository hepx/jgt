package org.hepx.jgt.common.mobile;

import org.apache.commons.lang3.StringUtils;
import org.hepx.jgt.common.exception.JgtException;

import java.util.regex.Pattern;

/**
 * 手机号码验证器
 * User: hepanxi
 * Date: 14-10-20
 * Time: 下午2:35
 */
public class MobileVerifier {

    public final static String M_PREFIX = "+86";

    /**
     * 截掉+86开头
     * @param mobile
     * @return
     */
    public static String catMobile(String mobile){
        if(mobile.startsWith(M_PREFIX)){
            return mobile.substring(M_PREFIX.length());
        }else{
            return mobile;
        }
    }

    /**
     * 验证手机号码长度
     * @param mobile
     * @return
     */
    public static boolean verifyLength(String mobile) throws JgtException {
        if (mobile.length()!=11) {
            throw new JgtException("手机号码长度不符");
        }
        return true;
    }

    /**
     * 验证手机号码字符
     * @param mobile
     * @return
     */
    public static boolean verifyChar(String mobile)throws JgtException{
        if(!StringUtils.isNumeric(mobile)){
            throw new JgtException("手机号码包含非法字符");
        }
        return true;
    }

    /**
     * 验证手机号段
     * @param mobile
     * @return
     */
    public static boolean verifySegment(String mobile)throws JgtException{
        if(!Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}")
                .matcher(mobile).matches()){
            throw new JgtException("手机号段格式错误");
        }
        return true;
    }

    /**
     * 按流程流程验证手机号
     * @param mobile
     * @return
     */
    public static boolean verify(String mobile)throws JgtException{
        //第一步验证手机号有没有非法字符
        verifyChar(mobile);
        //第二步验证手机号码长度
        verifyLength(mobile);
        //第三步验证手机号段
        verifySegment(mobile);
        return true;
    }

}
