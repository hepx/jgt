package org.hepx.jgt.webapi.config;

import org.hepx.jgt.common.json.JsonUtil;
import org.hepx.jgt.webapi.config.annotation.IgnoreResponseMsg;
import org.hepx.jgt.webapi.domain.ResponseMsg;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应体统一封装处理
 *
 * @author hepx
 * @date 2020/11/12 17:03
 */
@RestControllerAdvice(basePackages = {"org.hepx.jgt.webapi.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResponseMsg那就没有必要进行额外的操作，返回false
        // 如果方法上加了IgnoreResponseMsg也没有必要进行额外的操作 返回false
        return !(methodParameter.getParameterType().equals(ResponseMsg.class) || methodParameter.hasMethodAnnotation(IgnoreResponseMsg.class));
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //统一封装为ResponseMsg  String类型直接转为JSON字符串返回
        if (methodParameter.getParameterType().equals(String.class)) {
            return JsonUtil.toJson(ResponseMsg.success(o));
        }
        return ResponseMsg.success(o);
    }
}
