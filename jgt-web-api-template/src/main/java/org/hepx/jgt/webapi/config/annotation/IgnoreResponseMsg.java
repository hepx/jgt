package org.hepx.jgt.webapi.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口上使用忽略ResponseMsg结构直接返回。
 *
 * @author hepx
 * @date 2020/11/13 10:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IgnoreResponseMsg {
}
