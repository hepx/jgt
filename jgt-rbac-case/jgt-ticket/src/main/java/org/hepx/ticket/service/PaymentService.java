package org.hepx.ticket.service;

import org.hepx.ticket.entity.Payment;

import java.util.List;

public interface PaymentService {

    public Payment createPayment(Payment payment);

    public int updatePayment(Payment payment);

    public int deletePayment(Long paymentId);

    public Payment findOne(Long paymentId);

    public List<Payment> findAll();

    public List<Payment> findByTradeId(Long tradeId);

}
