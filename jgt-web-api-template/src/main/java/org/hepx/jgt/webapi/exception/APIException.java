package org.hepx.jgt.webapi.exception;

import lombok.Data;
import org.hepx.jgt.webapi.config.ResponseCode;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/11/12 15:32
 */
@Data
public class APIException extends RuntimeException {

    /**
     * 异常代码
     */
    private String code;
    /**
     * 异常信息
     */
    private String msg;


    public APIException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }
}
