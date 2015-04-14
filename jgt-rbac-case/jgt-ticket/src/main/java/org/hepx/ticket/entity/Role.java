package org.hepx.ticket.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Alias("t_role")
public class Role extends IdEntity implements Serializable {
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private List<Long> resourceIds; //拥有的资源
    private String resourceIdsStr; //拥有资源字符串模式
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getResourceIds() {
        if (this.resourceIdsStr == null) {
            return null;
        }
        String[] resourceIdStrs = this.resourceIdsStr.split(",");
        this.resourceIds = new ArrayList<Long>();
        for (String resourceIdStr : resourceIdStrs) {
            if (StringUtils.isEmpty(resourceIdStr)) {
                continue;
            }
            this.resourceIds.add(Long.valueOf(resourceIdStr));
        }
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
        if(!CollectionUtils.isEmpty(resourceIds)){
            StringBuffer sb = new StringBuffer();
            for(Long resourceId:resourceIds){
                sb.append(resourceId);
                sb.append(",");
            }
            setResourceIdsStr(sb.substring(0,sb.lastIndexOf(",")));
        }
    }

    public String getResourceIdsStr() {
        return resourceIdsStr;
    }

    public void setResourceIdsStr(String resourceIdsStr) {
        this.resourceIdsStr = resourceIdsStr;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", resourceIds=" + resourceIds +
                ", resourceIdsStr='" + resourceIdsStr + '\'' +
                ", available=" + available +
                '}';
    }
}
