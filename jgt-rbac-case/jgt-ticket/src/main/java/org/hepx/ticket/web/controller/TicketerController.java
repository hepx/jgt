package org.hepx.ticket.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.jgt.common.json.JsonUtil;
import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.service.TicketService;
import org.hepx.ticket.service.TradeService;
import org.hepx.ticket.web.ResponseResult;
import org.hepx.ticket.web.TicketVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: hepanxi
 * Date: 15-4-20
 * Time: 下午4:48
 */
@Controller
@RequestMapping("/ticket")
public class TicketerController {

    private static Logger logger = LoggerFactory.getLogger(TicketerController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TradeService tradeService;

    @RequiresPermissions("ticket:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("ticketList", ticketService.findAll());
        return "ticket/list";
    }

    @RequiresPermissions("ticket:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("op", "新增");
        model.addAttribute("tradeNo", tradeService.getTradeNo());
        model.addAttribute("ticketData", findByStatus(Ticket.TicketStatus.EXISTED));
        return "ticket/edit";
    }

    @RequiresPermissions("ticket:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Map create(@RequestBody String requestData) {
        try {
            TicketVo vo = JsonUtil.json2Object(requestData, TicketVo.class);
            tradeService.createTrade(vo);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }

    @RequiresPermissions("ticket:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ticket", ticketService.findOne(id));
        model.addAttribute("op", "修改");
        return "ticket/edit";
    }

    @RequiresPermissions("ticket:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Ticket ticket, RedirectAttributes redirectAttributes) {
        ticketService.updateTicket(ticket);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/ticket";
    }

    @RequiresPermissions("ticket:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @ResponseBody
    public Map delete(@PathVariable("id") Long id) {
        try {
            ticketService.deleteTicket(id);
            return ResponseResult.buildSuccessResult().toMap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseResult.buildFailResult().toMap();
        }
    }

    /**
     * 票据盈利分析
     * @param model
     * @return
     */
    @RequiresPermissions("analysis:view")
    @RequestMapping(value = "/analysis",method = RequestMethod.GET)
    public String analysis(Model model){
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("ticketList",tickets);
        return "ticket/analysis";
    }

    private String findByStatus(Ticket.TicketStatus ticketStatus) {
        //在库票据
        List<Ticket> ticketList = ticketService.findByStatus(ticketStatus);
        //构建select格式数据
        Map<Long, String> options = new HashMap<Long, String>();
        options.put(0L, "请选择");
        for (Ticket t : ticketList) {
            options.put(t.getId(), t.getTicketNo());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tickets", ticketList);
        map.put("ticketNos", options);
        return JsonUtil.objectToJson(map);
    }
}
