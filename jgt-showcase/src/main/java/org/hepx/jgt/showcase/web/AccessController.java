package org.hepx.jgt.showcase.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色+权限
 * @author: Koala
 * @Date: 14-8-7 上午9:59
 * @Version: 1.0
 */
@Controller
@RequestMapping("/access")
public class AccessController {

    private static Logger logger = LoggerFactory.getLogger(AccessController.class);

    @RequestMapping("roles")
    public String listByRole(Model model){

        return "sys/role";
    }

    @RequestMapping("permissions")
    public String listByPermission(Model modle){

        return "sys/permission";
    }
}
