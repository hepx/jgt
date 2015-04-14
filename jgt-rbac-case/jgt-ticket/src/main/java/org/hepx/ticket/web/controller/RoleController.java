package org.hepx.ticket.web.controller;

import org.hepx.ticket.entity.Role;
import org.hepx.ticket.service.ResourceService;
import org.hepx.ticket.service.RoleService;
import org.hepx.ticket.web.ResponseResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.ticket.service.ResourceService;
import org.hepx.ticket.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    private static Logger logger  = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roleList", roleService.findAll());
        return "role/list";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("op", "新增");
        return "role/edit";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Role role, RedirectAttributes redirectAttributes) {
        roleService.createRole(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        return "role/edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map showDeleteForm(@PathVariable("id") Long id, Model model) {
        try {
            roleService.deleteRole(id);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ResponseResult.buildFailResult().toMap();
        }
    }

}
