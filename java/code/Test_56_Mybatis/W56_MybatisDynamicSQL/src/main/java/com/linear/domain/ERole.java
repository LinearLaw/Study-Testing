package com.linear.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 56.10 角色类
 */
public class ERole implements Serializable {

    private Integer roleId;
    private String roleName;
    private String roleDesc;

    // 多对多关系映射，使用一个集合来存与当前角色关联的User
    private List<EUser> ulist;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<EUser> getUlist() {
        return ulist;
    }

    public void setUlist(List<EUser> ulist) {
        this.ulist = ulist;
    }

    @Override
    public String toString() {
        return "ERole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", ulist=" + ulist +
                '}';
    }
}
