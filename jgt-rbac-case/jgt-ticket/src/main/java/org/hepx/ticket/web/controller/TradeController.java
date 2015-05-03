package org.hepx.ticket.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hepx.jgt.common.date.DateUtil;
import org.hepx.ticket.entity.Trade;
import org.hepx.ticket.service.PaymentService;
import org.hepx.ticket.service.TicketService;
import org.hepx.ticket.service.TradeService;
import org.hepx.ticket.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: hepx
 * Date: 15-5-1
 * Time: 下午3:23
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    private static Logger logger = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PaymentService paymentService;

    @RequiresPermissions("trade:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "ticketNo", required = false) String ticketNo,
                       @RequestParam(value = "startTime", required = false) String startTime,
                       @RequestParam(value = "endTime", required = false) String endTime, Model model) {

        if (startTime == null) {
            startTime = DateUtil.formateDate(new Date());
        }
        if (endTime == null) {
            endTime = DateUtil.formateDate(new Date());
        }
        List<Trade> tradeList = tradeService.findByCondition(ticketNo,startTime,endTime);
        model.addAttribute("ticketNo",ticketNo);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime",endTime);
        model.addAttribute("tradeList", tradeList);
        return "trade/list";
    }

    @RequiresPermissions("trade:view")
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String getOne(@PathVariable("id") Long id, Model model) {
        model.addAttribute("trade", tradeService.findOne(id));
        model.addAttribute("inTickets", ticketService.findInTicketByTradeId(id));
        model.addAttribute("outTickets", ticketService.findOutTicketByTradeId(id));
        model.addAttribute("payments",paymentService.findByTradeId(id));
        return "trade/detail";
    }

    @RequiresPermissions("trade:update")
    @RequestMapping(value="/{id}/check",method = RequestMethod.GET)
    @ResponseBody
    public Map update(@PathVariable("id")Long id){
        try {
            Trade trade = tradeService.findOne(id);
            if(trade != null){
                trade.setTradeStatus(Trade.TradeStatus.CHECKED);
                tradeService.updateTrade(trade);
                return ResponseResult.buildSuccessResult().toMap();
            }else{
                throw new Exception("交易不存在!");
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ResponseResult.buildFailResult().toMap();
        }
    }
}
