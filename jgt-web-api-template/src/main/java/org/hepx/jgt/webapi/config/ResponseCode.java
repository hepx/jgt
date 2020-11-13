package org.hepx.jgt.webapi.config;

import lombok.Getter;

/**
 * 响应码 枚举
 *
 * @author hepx
 * @date 2020/11/12 16:15
 */
@Getter
public enum ResponseCode {

    FAILED("0", "操作失败"),

    SUCCESS("1", "操作成功"),

    /********* 1xxx 通用错误码 *****/
    PARAMS_INVALID("1000", "参数无效"),
    SYS_ERROR("1001", "内部错误"),
    PROCESS_TIMEOUT("1002", "处理超时"),

    /********* 2xxx 授权 认证错误码 *****/
    USER_INVALID("2000", "用户无效"),
    LOGIN_FAILED("2001", "登录失败"),
    USER_DISABLED("2002", "用户被禁"),
    NO_PERMISSION("2003", "无权访问"),

    /********* 3xxx 接口调用错误码 *****/
    API_DISABLED("3000", "接口不可用"),
    API_KEY_INVALID("3001", "api key无效"),
    SIGNATURE_INVALID("3002", "签名无效"),

    /********* 测试扩展ExceptionCode *****/
    USER_PARAMS_INVALID("10001", "用户名无效"),
    EMAIL_PROCESS_TIMEOUT("10002", "邮箱无效");

    private String code;

    private String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
