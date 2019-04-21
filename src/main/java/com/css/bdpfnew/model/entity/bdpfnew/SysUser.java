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
@Table(name = "SYS_USER", schema = "BDPFNEW2018")
public class SysUser extends BaseEntity{

    private String username;

    private String password;

    private String nickname;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<SysRole> roles = new HashSet<SysRole>();

    private String cityid;

    private String cityName;

    @Column(nullable = false, columnDefinition = "INT default 0",insertable = false)
    private Integer delFlag;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
