package org.hepx.ticket.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.hepx.ticket.entity.Resource;
import org.hepx.ticket.service.ResourceService;
import org.hepx.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;


@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionDAO sessionDAO;

    @RequestMapping("/")
    public String index(Model model) {
        Set<String> permissions = userService.findPermissions(getCurrentUser());
        List<Resource> menus = resourceService.findMenus(permissions);
        //左侧菜单
        model.addAttribute("menus", menus);
        SecurityUtils.getSubject().getSession().setAttribute("menus",menus);
        //当前在线用户
        model.addAttribute("inline",sessionDAO.getActiveSessions().size());
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 获得当前登录用户
     * @return
     */
    public String getCurrentUser(){
        return (String)SecurityUtils.getSubject().getPrincipal();
    }

}
