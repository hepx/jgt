package org.hepx.jgt.showcase.web;

import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.common.json.JsonResult;
import org.hepx.jgt.showcase.domain.Account;
import org.hepx.jgt.showcase.domain.BankCard;
import org.hepx.jgt.showcase.domain.DataTablePage;
import org.hepx.jgt.showcase.service.AccountService;
import org.hepx.jgt.showcase.service.BankCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联盟帐户
 *
 * @author: Koala
 * @Date: 14-7-29 下午2:39
 * @Version: 1.0
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private BankCardService bankCardService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String initList(@RequestParam(value = "status", required = false) Integer status, Model model) {
        model.addAttribute("page_title", (status != null && status == 0) ? "待审核用户" : "用户列表");
        model.addAttribute("status", status);
        return "/account/list";
    }

    /**
     * 分页查询帐户信息
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public DataTablePage<Account> processList(RequestParams requestParams,Integer status) {
        return this.accountService.list(status, requestParams);
    }

    /**
     * 根据ID查询帐户信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable("id") Long id, Model model) {
        Account account = this.accountService.findOne(id);
        List<BankCard> banks = this.bankCardService.list(account.getId());
        model.addAttribute("account", account);
        if (banks != null && banks.size() > 0) {
            model.addAttribute("bank", banks.get(0));
        }
        return "/account/detail";
    }

    /**
     * 初始化帐户信息
     * @param id
     * @param model
     * @return
     */
/*    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String initUpdate(@PathVariable("id")Long id,Model model){
        model.addAttribute("account",this.accountService.findOne(id));
        return "/account/detail";
    }*/

    /**
     * 更新帐户
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "update/{id}/{status}")
    @ResponseBody
    public JsonResult processUpdate(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        if (id != null) {
            Account account = this.accountService.findOne(id);
            account.setStatus(status);
            this.accountService.update(account);
            return new JsonResult(true);
        } else {
            return new JsonResult("缺少必要的参数。");
        }
    }

}
