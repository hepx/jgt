package org.hepx.ticket.mapper;

import org.hepx.ticket.entity.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:39
 */
@Repository
public interface BankAccountMapper {
    
    public int createBankAccount(BankAccount bankAccount);

    public int updateBankAccount(BankAccount bankAccount);

    public int deleteBankAccount(Long bankAccountId);

    public BankAccount findOne(Long bankAccountId);

    public List<BankAccount> findAll();

    public List<BankAccount> findJson(String account);

}
