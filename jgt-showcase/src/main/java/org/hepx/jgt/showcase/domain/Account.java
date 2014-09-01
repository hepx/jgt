package org.hepx.jgt.showcase.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户信息
 */
@Entity
@Table(name = "t_account")
public class Account extends IdEntity {

    protected Account() {
    }

    public Account(String realName, String idCard, String mobilePhone, String email, Integer status, String userIdentifying, Date createTime,
                   Date updateTime, String taxType, String taxNo, String taxRate) {
        this.realName = realName;
        this.idCard = idCard;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.status = status;
        this.userIdentifying = userIdentifying;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.taxType = taxType;
        this.taxNo = taxNo;
        this.taxRate = taxRate;
    }

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String idCard;
    /**
     * 手机号码
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
//    /**
//     * 账户类型，0，个人，1，公司
//     */
//    private Boolean accountType;
    /**
     * 审核状态,0，审核中，1，审核通过，2，驳回 3:冻结,
     */
    private Integer status;
    /**
     * 用户唯一id ，用来绑定账号，与联盟个人信息
     */
    private String userIdentifying;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 更新时间
	 */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
    /**
     *  纳税类型
     */
    private String taxType;
    /**
     * 税号
     */
    private String taxNo;
    /**
     * 税率
     */
    private String taxRate;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserIdentifying() {
        return userIdentifying;
    }

    public void setUserIdentifying(String userIdentifying) {
        this.userIdentifying = userIdentifying;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", userIdentifying='" + userIdentifying + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", taxType='" + taxType + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", taxRate='" + taxRate + '\'' +
                '}';
    }
}