package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * User: hepanxi
 * Date: 15-4-21
 * Time: 下午1:54
 */
@Alias("t_payment")
public class Payment extends IdEntity implements Serializable {

    private PayMode payMode; //支付方式

    private PayType payType;//支付类型

    private TransferType transferType;//转帐类型

    private String account; //支付帐号 现金方式没有此项

    private double payMoney;//支付金额

    private Long tradeId;//所属交易

    //支付方式
    public static enum PayMode{
        PAY("支付"),COLLECT("收取");
        private String value;
        private PayMode(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    //支付类型
    public static enum PayType{
        CASH("现金"),TRANSFER("转帐");
        private String value;
        private PayType(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    //转帐类型
    public static enum TransferType{
        EBANK("网银"),POS("刷卡机");
        private String value;
        private TransferType(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public PayMode getPayMode() {
        return payMode;
    }

    public void setPayMode(PayMode payMode) {
        this.payMode = payMode;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payType=" + payType +
                ", account='" + account + '\'' +
                ", payMoney=" + payMoney +
                ", tradeId=" + tradeId +
                '}';
    }
}
