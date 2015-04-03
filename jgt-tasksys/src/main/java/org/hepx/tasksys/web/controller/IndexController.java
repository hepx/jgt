package org.hepx.tasksys.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.hepx.tasksys.entity.Resource;
import org.hepx.tasksys.service.ResourceService;
import org.hepx.tasksys.service.TaskService;
import org.hepx.tasksys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
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
    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String index(Model model) {
        Set<String> permissions = userService.findPermissions(getCurrentUser());
        List<Resource> menus = resourceService.findMenus(permissions);
        //菜单
        model.addAttribute("menus", menus);
        SecurityUtils.getSubject().getSession().setAttribute("menus",menus);
        //在线人数
        model.addAttribute("inline", sessionDAO.getActiveSessions().size());
        //任务数
        model.addAttribute("totalTask",taskService.getTotalTask());
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
