package org.hepx.ticket.service;

import org.hepx.ticket.entity.Ticket;
import org.hepx.ticket.entity.Trade;
import org.hepx.ticket.web.TicketVo;

import java.util.List;

public interface TradeService {

    public Trade createTrade(Trade trade);

    public int updateTrade(Trade trade);

    public int deleteTrade(Long tradeId);

    public Trade findOne(Long tradeId);

    public List<Trade> findAll();

    public String getTradeNo();

    public void crateTrade(TicketVo vo);

}
