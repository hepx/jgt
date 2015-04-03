package org.hepx.rbac.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Alias("user")
public class User extends IdEntity implements Serializable {
    private Long organizationId; //所属公司
    private String username; //用户名
    private String email;//邮箱
    private String mobile;//手机号码
    private String password; //密码
    private String salt; //加密密码的盐
    private List<Long> roleIds; //拥有的角色列表
    private String roleIdsStr; //拥有的角色列表字符形式
    private Boolean locked;//是否锁定用户

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public List<Long> getRoleIds() {
        if (this.roleIdsStr == null) {
            return null;
        }
        String[] roleIdStrs = roleIdsStr.split(",");
        this.roleIds = new ArrayList<Long>();
        for(String roleIdStr : roleIdStrs) {
            if(StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            this.roleIds.add(Long.valueOf(roleIdStr));
        }
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        if(!CollectionUtils.isEmpty(roleIds)){
            StringBuilder s = new StringBuilder();
            for(Long roleId : roleIds) {
                s.append(roleId);
                s.append(",");
            }
            setRoleIdsStr(s.substring(0,s.lastIndexOf(",")));
        }
    }

    public String getRoleIdsStr() {
        return roleIdsStr;
    }

    public void setRoleIdsStr(String roleIdsStr) {
        this.roleIdsStr = roleIdsStr;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ",locked=" + locked +
                ", organizationId=" + organizationId +
                ", password='" + password + '\'' +
                ", roleIds=" + roleIds +
                ", roleIdsStr='" + roleIdsStr + '\'' +
                ", salt='" + salt + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
