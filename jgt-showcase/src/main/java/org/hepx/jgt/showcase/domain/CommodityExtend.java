package org.hepx.jgt.showcase.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商品推广信息
 * @author: Koala
 * @Date: 14-7-29 上午10:11
 * @Version: 1.0
 */
@Entity
@Table(name = "t_commodity_extend")
public class CommodityExtend extends IdEntity implements Serializable {

	public CommodityExtend() {
	}

    public CommodityExtend(String url, String name, String pictureUrl, String description, BigDecimal price, BigDecimal rate, Date startTime,
                           Date endTime, Long commodityId, Date createTime, Date updateTime, Boolean status) {
        this.url = url;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.price = price;
        this.rate = rate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.commodityId = commodityId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    /**
	 * 商品链接
	 */
	private String url;
	/**
	 * 商品名
	 */
	private String name;
    /**
     * 图片链接
     */
    private String pictureUrl;
    /**
     *商品描述
     */
    private String description;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 佣金
	 */
	private BigDecimal rate;
	/**
	 * 推广开始时间
	 */
    @JSONField(format = "yyyy-MM-dd")
	private Date startTime;
	/**
	 * 推广结束时间
	 */
    @JSONField(format = "yyyy-MM-dd")
    private Date endTime;
	/**
	 * 商品id
	 */
	private Long commodityId;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
    /**
     * 状态 0 未发布，1：已发布
     */
    private Boolean status;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommodityExtend{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", commodityId=" + commodityId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}