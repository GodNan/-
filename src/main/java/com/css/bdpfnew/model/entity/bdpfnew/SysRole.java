package com.css.bdpfnew.model.entity.bdpfnew;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/5/25 14:30
 * @Version: 1.0
 * @Description:
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "SYS_ROLE", schema = "BDPFNEW2018", catalog = "")
public class SysRole extends BaseEntity{

    private String roleName;

    private String roleDescribe;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<SysUser> users = new HashSet<SysUser>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<SysPermission> permissions = new HashSet<SysPermission>();

    @Column(nullable = false, columnDefinition = "INT default 0",insertable = false)
    private Integer delFlag;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<SysUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }

    public Set<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }
}
