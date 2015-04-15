package org.hepx.ticket.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.ticket.entity.BankAccount;
import org.hepx.ticket.service.BankAccountService;
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
 * 银行帐户
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午9:56
 */
@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    
    private static Logger logger = LoggerFactory.getLogger(BankAccountController.class);
    
    @Autowired
    private BankAccountService bankAccountService;

    @RequiresPermissions("bankaccount:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("bankAccountList", bankAccountService.findAll());
        return "bankAccount/list";
    }

    @RequiresPermissions("bankaccount:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("op", "新增");
        return "bankAccount/edit";
    }

    @RequiresPermissions("bankaccount:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(BankAccount bankAccount, RedirectAttributes redirectAttributes) {
        bankAccountService.createBankAccount(bankAccount);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/bankaccount";
    }

    @RequiresPermissions("bankaccount:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bankAccount", bankAccountService.findOne(id));
        model.addAttribute("op", "修改");
        return "bankAccount/edit";
    }

    @RequiresPermissions("bankaccount:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(BankAccount bankAccount, RedirectAttributes redirectAttributes) {
        bankAccountService.updateBankAccount(bankAccount);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/bankaccount";
    }

    @RequiresPermissions("bankaccount:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@PathVariable("id") Long id) {
        try {
            bankAccountService.deleteBankAccount(id);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }
}
