package org.hepx.jgt.webapi.config;

import org.hepx.jgt.webapi.config.annotation.ExceptionCode;
import org.hepx.jgt.webapi.domain.ResponseMsg;
import org.hepx.jgt.webapi.exception.APIException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理类
 *
 * @author hepx
 * @date 2020/11/12 15:27
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 参数验证统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMsg<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 参数的Class对象
        Class<?> parameterType = e.getParameter().getParameterType();
        // 拿到错误的字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();
        // 获取Field对象上的自定义注解
        ExceptionCode annotation = parameterType.getDeclaredField(fieldName).getAnnotation(ExceptionCode.class);
        // 有注解的话就返回注解的响应信息
        if (annotation != null) {
            return new ResponseMsg<>(annotation.value(), objectError.getDefaultMessage());
        }
        // 然后提取错误提示信息进行返回
        return new ResponseMsg<>(ResponseCode.PARAMS_INVALID, objectError.getDefaultMessage());
    }

    /**
     * 自定义接口异常类处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(APIException.class)
    public ResponseMsg<String> APIExceptionHandler(APIException e) {
        return ResponseMsg.failure(e.getMsg());
    }
}
