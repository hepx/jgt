package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:39
 */
@Repository
public interface PaymentMapper {
    
    public int createPayment(Payment payment);

    public int updatePayment(Payment payment);

    public int deletePayment(Long paymentId);

    public Payment findOne(Long paymentId);

    public List<Payment> findAll();

    public List<Payment> findByTradeId(Long tradeId);

}
