package org.hepx.jgt.showcase.web;

import org.hepx.jgt.common.json.JsonResult;
import org.hepx.jgt.showcase.domain.CommissionRate;
import org.hepx.jgt.showcase.service.CommissionRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 佣金比率
 *
 * @author: Koala
 * @Date: 14-7-29 上午11:43
 * @Version: 1.0
 */
@Controller
@RequestMapping("/rate")
public class CommissionRateController {

    private static Logger logger = LoggerFactory.getLogger(CommissionRateController.class);

    @Autowired
    private CommissionRateService commissionRateService;

    /**
     * rate 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("rates", this.commissionRateService.list());
        return "rate/list";
    }

    /**
     * rate详情
     *
     * @param id
     * @param model
     * @return
     */
/*    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String find(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rate", this.commissionRateService.find(id));
        model.addAttribute("enableEdit", false);
        return "rate/edit";
    }*/

    /**
     * 初始化增加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String initAdd(Model model) {
        model.addAttribute("enableEdit", true);
        return "rate/edit";
    }

    /**
     * 初始化更新
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String initUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rate", this.commissionRateService.find(id));
        model.addAttribute("enableEdit", true);
        return "rate/edit";
    }

    /**
     * 保存或更新rate
     *
     * @param commissionRate
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public RedirectView save(CommissionRate commissionRate,Model model) {
        this.commissionRateService.saveOrUpdate(commissionRate);
        RedirectView r=new RedirectView("/rate/list");
        return r;
    }

    @RequestMapping(value="delete/{id}",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult delete(@PathVariable("id")Long id){
        try {
            this.commissionRateService.delete(id);
            return new JsonResult(true);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new JsonResult("删除失败").addData("errorCode",e.getMessage());
        }
    }

}
