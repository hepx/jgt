package org.hepx.jgt.showcase.web;

import org.hepx.jgt.common.datatable.RequestParams;
import org.hepx.jgt.common.json.JsonResult;
import org.hepx.jgt.showcase.domain.CommodityExtend;
import org.hepx.jgt.showcase.domain.DataTablePage;
import org.hepx.jgt.showcase.service.CommodityExtendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 商品推广
 *
 * @author: Koala
 * @Date: 14-7-29 下午5:10
 * @Version: 1.0
 */
@Controller
@RequestMapping("/extend")
public class CommodityExtendController {

    private static Logger logger = LoggerFactory.getLogger(CommissionRateController.class);

    @Autowired
    private CommodityExtendService commodityExtendService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String initList() {
        return "/extend/list";
    }

    /**
     * 分页查询推广商品
     * @return
     */
    @RequestMapping(value="list",method = RequestMethod.POST)
    @ResponseBody
    public DataTablePage<CommodityExtend> processList(RequestParams requestParams,String keyword) {
        requestParams.getSearch().setValue(keyword);
        return  this.commodityExtendService.list(requestParams);
    }

    /**
     * 根据ID查询推广信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable("id") Long id, Model model) {
        model.addAttribute("account", this.commodityExtendService.findOne(id));
        return "/extend/detail";
    }

    /**
     * 更新商品状态
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "update/{id}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateStatus(@PathVariable("id") Long id, @PathVariable("status") Boolean status) {
        try {
            CommodityExtend c = this.commodityExtendService.findOne(id);
            if (c != null) {
                c.setStatus(status);
                this.commodityExtendService.saveOrUpdate(c);
                return new JsonResult(true);
            } else {
                return new JsonResult("商品信息不存在。");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new JsonResult("程序发生错误。");
        }
    }

    /**
     * 更新推广商品信息
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult update(CommodityExtend commodityExtend) {
        try {
            CommodityExtend c = this.commodityExtendService.findOne(commodityExtend.getId());
            if (c != null) {
                c.setRate(commodityExtend.getRate());
                c.setStartTime(commodityExtend.getStartTime());
                c.setEndTime(commodityExtend.getEndTime());
                c = this.commodityExtendService.saveOrUpdate(c);
            }
            return new JsonResult(true).addData("extend", c);
        } catch (Exception e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            logger.error(e.getMessage(), e);
            return new JsonResult("保存失败！");
        }
    }

    /**
     * 加入推广
     *
     * @param ids
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public JsonResult add(@RequestParam("ids") Collection<Long> ids) {
        try {
            this.commodityExtendService.add(ids);
            return new JsonResult(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new JsonResult("添加推广商品失败。");
        }
    }

}
