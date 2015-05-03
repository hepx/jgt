package org.hepx.ticket.service;

import org.hepx.ticket.entity.Trade;
import org.hepx.ticket.web.TicketVo;

import java.util.Date;
import java.util.List;

public interface TradeService {

    public Trade createTrade(Trade trade);

    public int updateTrade(Trade trade);

    public int deleteTrade(Long tradeId);

    public Trade findOne(Long tradeId);

    public List<Trade> findAll();

    public List<Trade> findByCondition(String ticketNo,String startTime,String endTime);

    public String getTradeNo();

    public void createTrade(TicketVo vo);

    public long statTradeByAll();

    public long statTradeByDay(Date date);

}
