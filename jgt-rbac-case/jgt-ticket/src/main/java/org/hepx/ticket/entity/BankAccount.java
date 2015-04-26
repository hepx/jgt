package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * User: hepanxi
 * Date: 15-4-15
 * Time: 上午8:50
 */
@Alias("t_bank_account")
public class BankAccount extends IdEntity implements Serializable {

    private String bankName;

    private String account;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
