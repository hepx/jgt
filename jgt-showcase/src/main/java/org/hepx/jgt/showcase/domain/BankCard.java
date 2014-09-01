package org.hepx.jgt.showcase.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 银行卡信息
 */
@Entity
@Table(name = "t_bank_card")
public class BankCard extends IdEntity {

    protected BankCard() {
    }

	public BankCard(String cardNo, String cardName, String openBankProvince, String openBankCity, String openBankDistrict, String openBankName, String unionpayNo, Long userId) {
		this.cardNo = cardNo;
		this.cardName = cardName;
		this.openBankProvince = openBankProvince;
		this.openBankCity = openBankCity;
		this.openBankDistrict = openBankDistrict;
		this.openBankName = openBankName;
		this.unionpayNo = unionpayNo;
		this.userId = userId;
	}

	/**
     * 银行卡卡号
     */
    private String cardNo;
    /**
     * 收款银行
     */
    private String cardName;

	/**
	 * 开户省
	 */
	private String openBankProvince;
	/**
	 * 开户市
	 */
	private String openBankCity;
	/**
	 * 开户区
	 */
	private String openBankDistrict;
    /**
     * 开户行
     */
    private String openBankName;
    /**
     * 银联号
     */
    private String unionpayNo;
    /**
     * 用户id
     */
    private Long userId;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

	public String getOpenBankProvince() {
		return openBankProvince;
	}

	public void setOpenBankProvince(String openBankProvince) {
		this.openBankProvince = openBankProvince;
	}

	public String getOpenBankCity() {
		return openBankCity;
	}

	public void setOpenBankCity(String openBankCity) {
		this.openBankCity = openBankCity;
	}

	public String getOpenBankDistrict() {
		return openBankDistrict;
	}

	public void setOpenBankDistrict(String openBankDistrict) {
		this.openBankDistrict = openBankDistrict;
	}

	public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public String getUnionpayNo() {
        return unionpayNo;
    }

    public void setUnionpayNo(String unionpayNo) {
        this.unionpayNo = unionpayNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "BankCard{" +
				"cardNo='" + cardNo + '\'' +
				", cardName='" + cardName + '\'' +
				", openBankProvince='" + openBankProvince + '\'' +
				", openBankCity='" + openBankCity + '\'' +
				", openBankDistrict='" + openBankDistrict + '\'' +
				", openBankName='" + openBankName + '\'' +
				", unionpayNo='" + unionpayNo + '\'' +
				", userId=" + userId +
				'}';
	}
}