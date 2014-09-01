package org.hepx.jgt.showcase.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


/**
 * 佣金比率
 * @author: Koala
 * @Date: 14-7-29 上午10:11
 * @Version: 1.0
 */
@Entity
@Table(name = "t_commission_rate")
public class CommissionRate extends IdEntity {

	protected CommissionRate() {
    }

    public CommissionRate(String category, String description, BigDecimal rate) {
        this.category = category;
        this.description = description;
        this.rate = rate;
    }

    /**
     * 佣金类型 e.g  锅，铲
     */
    private String category;

    /**
     * 描述
     */
    private String description;

	/**
	 * 佣金比率
	 */
	private BigDecimal rate;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CommissionRate{" +
                "category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}