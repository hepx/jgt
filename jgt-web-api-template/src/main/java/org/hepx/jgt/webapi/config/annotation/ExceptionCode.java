package org.hepx.jgt.webapi.config.annotation;

import org.hepx.jgt.webapi.config.ResponseCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 错误码的注解类，便于灵活扩展
 * 比如参数检验，每一个field都有业务上的code和msg
 *
 * @author hepx
 * @date 2020/11/13 10:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExceptionCode {
    /**
     * 使用响应码枚举值
     * @return
     */
    ResponseCode value();
}
