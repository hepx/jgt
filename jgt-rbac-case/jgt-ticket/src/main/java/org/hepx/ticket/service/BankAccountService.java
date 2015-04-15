package org.hepx.ticket.service;

import org.hepx.ticket.entity.BankAccount;

import java.util.List;

public interface BankAccountService {

    public BankAccount createBankAccount(BankAccount bankAccount);

    public int updateBankAccount(BankAccount bankAccount);

    public int deleteBankAccount(Long bankAccountId);

    public BankAccount findOne(Long bankAccountId);

    public List<BankAccount> findAll();

}
