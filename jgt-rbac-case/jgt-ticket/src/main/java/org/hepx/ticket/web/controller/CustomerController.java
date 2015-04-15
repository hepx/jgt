package org.hepx.ticket.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.ticket.entity.Customer;
import org.hepx.ticket.service.CustomerService;
import org.hepx.ticket.web.ResponseResult;
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

/**
 * 客户
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午9:50
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequiresPermissions("customer:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customerList", customerService.findAll());
        return "customer/list";
    }

    @RequiresPermissions("customer:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("op", "新增");
        return "customer/edit";
    }

    @RequiresPermissions("customer:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/customer";
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerService.findOne(id));
        model.addAttribute("op", "修改");
        return "customer/edit";
    }

    @RequiresPermissions("customer:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.updateCustomer(customer);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/customer";
    }

    @RequiresPermissions("customer:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@PathVariable("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }

}
