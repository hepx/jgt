package org.hepx.ticket.service;

import org.hepx.ticket.entity.BankAccount;
import org.hepx.ticket.mapper.BankAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午9:41
 */
@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        bankAccountMapper.createBankAccount(bankAccount);
        return bankAccount;
    }

    @Override
    public int updateBankAccount(BankAccount bankAccount) {
        return bankAccountMapper.updateBankAccount(bankAccount);
    }

    @Override
    public int deleteBankAccount(Long bankAccountId) {
        return bankAccountMapper.deleteBankAccount(bankAccountId);
    }

    @Override
    public BankAccount findOne(Long bankAccountId) {
        return bankAccountMapper.findOne(bankAccountId);
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountMapper.findAll();
    }

    @Override
    public List<BankAccount> findJson(String account) {
        return bankAccountMapper.findJson(account);
    }
}
