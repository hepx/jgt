package org.hepx.ticket.web.controller;

import org.hepx.ticket.service.OrganizationService;
import org.hepx.ticket.entity.Organization;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.ticket.web.ResponseResult;
import org.hepx.ticket.entity.Organization;
import org.hepx.ticket.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @RequiresPermissions("organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/list";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        Organization parent = organizationService.findOne(parentId);
        model.addAttribute("parent", parent);
        model.addAttribute("op", "新增");
        return "organization/edit";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(Organization organization, RedirectAttributes redirectAttributes) {
        organizationService.createOrganization(organization);
        redirectAttributes.addFlashAttribute("msg", "新增公司成功");
        return "redirect:/organization";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("organization", organizationService.findOne(id));
        model.addAttribute("op", "修改");
        return "organization/edit";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Organization organization, RedirectAttributes redirectAttributes) {
        organizationService.updateOrganization(organization);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/organization";
    }

    @RequiresPermissions("organization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@PathVariable("id") Long id) {
        try {
            organizationService.deleteOrganization(id);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String showMoveForm(@PathVariable("sourceId") Long sourceId, Model model) {
        Organization source = organizationService.findOne(sourceId);
        model.addAttribute("source", source);
        model.addAttribute("targetList", organizationService.findAllWithExclude(source));
        return "organization/move";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    public String move(
            @PathVariable("sourceId") Long sourceId,
            @RequestParam("targetId") Long targetId) {
        Organization source = organizationService.findOne(sourceId);
        Organization target = organizationService.findOne(targetId);
        organizationService.move(source, target);
        return "redirect:/organization/success";
    }


}
