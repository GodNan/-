package com.css.bdpfnew.model.entity.bdpfnew;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/5/25 14:30
 * @Version: 1.0
 * @Description:
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "SYS_PERMISSION", schema = "BDPFNEW2018")
public class SysPermission extends BaseEntity{

    private String menuCode;

    private String menuName;

    private String functionCode;

    private String functionName;

    private Long orderBy;

    @JsonIgnore
    @Where(clause = "del_flag = 0")
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<SysRole> roles = new HashSet<SysRole>();

    @JsonIgnore
    @Where(clause = "del_flag = 0")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysPermission parentPermission;

    @JsonIgnore
    @OrderBy("ORDER_BY DESC")
    @Where(clause = "del_flag = 0")
    @OneToMany(targetEntity = SysPermission.class, cascade = { CascadeType.ALL }, mappedBy = "parentPermission",fetch = FetchType.LAZY)
    private List<SysPermission> childPermissions = new ArrayList<SysPermission>();

    @Column(nullable = false, columnDefinition = "INT default 0",insertable = false)
    private Integer delFlag;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Long getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Long orderBy) {
        this.orderBy = orderBy;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public SysPermission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(SysPermission parentPermission) {
        this.parentPermission = parentPermission;
    }

    public List<SysPermission> getChildPermissions() {
        return childPermissions;
    }

    public void setChildPermissions(List<SysPermission> childPermissions) {
        this.childPermissions = childPermissions;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 是否为菜单
     * @return
     */
    @Transient
    public Boolean getIsMenu(){
        if(StringUtils.isEmpty(this.functionCode) && StringUtils.isEmpty(this.functionName)){
            return true;
        }
        return false;
    }
}
