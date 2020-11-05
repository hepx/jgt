package org.hepx.jgt.ssoclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IDEA
 *
 * @author hepx
 * @date 2020/11/4 11:44
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Map<String, Object> getUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("username", "admin");
        user.put("age", 30);
        return user;
    }
}
