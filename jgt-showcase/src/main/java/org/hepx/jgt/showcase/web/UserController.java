package org.hepx.jgt.showcase.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统用户管理
 * @author: Koala
 * @Date: 14-8-7 上午10:09
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("list")
    public String list(Model model){

        return "sys/user";
    }
}
