package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.Trade;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:39
 */
@Repository
public interface TradeMapper {
    
    public int createTrade(Trade trade);

    public int updateTrade(Trade trade);

    public int deleteTrade(Long tradeId);

    public Trade findOne(Long tradeId);

    public List<Trade> findAll();

    public Trade findLast();
}
