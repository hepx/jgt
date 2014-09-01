package org.hepx.jgt.showcase.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 站点信息
 *
 * @author: Koala
 * @Date: 14-8-25 上午11:08
 * @Version: 1.0
 */
@Entity
@Table(name = "t_web_manage")
public class SiteInfo extends IdEntity {

    protected SiteInfo() {
    }

    public SiteInfo(String webName, String webUrl, String webType, Integer status, String description, String filing, Long userId,
                    Date creationTime, Date updateTime) {
        this.webName = webName;
        this.webUrl = webUrl;
        this.webType = webType;
        this.status = status;
        this.description = description;
        this.filing = filing;
        this.userId = userId;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }

    /**
     * 网站名称
     */
    private String webName;
    /**
     * 网站域名
     */
    private String webUrl;
    /**
     * 网站类型
     */
    private String webType;

    /**
     * 审核状态 ,0，未验证，1，审核中，2审核通过
     */
    private Integer status;
    /**
     * 网站描述
     */
    private String description;
    /**
     * 备案信息
     */
    private String filing;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 生成时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl.trim();
    }

    public String getWebType() {
        return webType;
    }

    public void setWebType(String webType) {
        this.webType = webType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFiling() {
        return filing;
    }

    public void setFiling(String filing) {
        this.filing = filing;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SiteInfo{" +
                "webName='" + webName + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", webType='" + webType + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", filing='" + filing + '\'' +
                ", userId=" + userId +
                ", creationTime=" + creationTime +
                ", updateTime=" + updateTime +
                '}';
    }
}