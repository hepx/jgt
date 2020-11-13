package org.hepx.jgt.webapi.domain;

import lombok.Data;
import org.hepx.jgt.webapi.config.ResponseCode;
import org.hepx.jgt.webapi.config.annotation.ExceptionCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/11/12 14:55
 */
@Data
public class User implements Serializable {

    private Long id;

    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    @ExceptionCode(value = ResponseCode.USER_PARAMS_INVALID)
    private String username;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String password;

    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ExceptionCode(value = ResponseCode.EMAIL_PROCESS_TIMEOUT)
    private String email;

}
