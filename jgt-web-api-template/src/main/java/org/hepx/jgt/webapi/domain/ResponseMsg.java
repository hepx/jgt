package org.hepx.jgt.webapi.domain;

import lombok.Data;
import org.hepx.jgt.webapi.config.ResponseCode;

import java.io.Serializable;

/**
 * 统一响应消息结构
 *
 * @author hepx
 * @date 2020/11/12 15:45
 */
@Data
public class ResponseMsg<T> implements Serializable {

    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public ResponseMsg(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public static ResponseMsg success(Object data) {
        return new ResponseMsg(ResponseCode.SUCCESS, data);
    }

    public static ResponseMsg failure(Object data) {
        return new ResponseMsg(ResponseCode.FAILED, data);
    }
}
