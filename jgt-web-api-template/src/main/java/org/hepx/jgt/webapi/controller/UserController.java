package org.hepx.jgt.webapi.controller;

import org.hepx.jgt.webapi.config.ResponseCode;
import org.hepx.jgt.webapi.config.annotation.IgnoreResponseMsg;
import org.hepx.jgt.webapi.domain.ResponseMsg;
import org.hepx.jgt.webapi.domain.User;
import org.hepx.jgt.webapi.exception.APIException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 接口
 *
 * @author hepx
 * @date 2020/11/12 15:11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/add")
    public ResponseMsg addUser(@RequestBody @Valid User user) {
        if (user.getId() != null) {
            throw new APIException(ResponseCode.API_DISABLED);
        }
        user.setId(1L);
        return ResponseMsg.success(user);
    }

    @GetMapping("/one")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return user;
    }

    @GetMapping("/name")
    public String getUsername() {
        return "amdin";
    }

    @IgnoreResponseMsg
    @PostMapping("/update")
    public User updateUser(@RequestBody @Valid User user) {
        user.setUsername("hepx");
        user.setEmail("hepanxi@163.com");
        return user;
    }
}
