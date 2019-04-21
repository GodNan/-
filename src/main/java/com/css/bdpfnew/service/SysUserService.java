package com.css.bdpfnew.service;

import com.css.bdpfnew.model.dto.DtoSysUser;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;

import java.util.Set;

/**
 * 
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
public interface SysUserService extends BaseService<SysUser, Long>{
    void save(DtoSysUser user, Set<SysRole> roles);
    void save(SysUser user);
    SysUser find(String uuid);

    void delete(SysUser user);

    void update(DtoSysUser user, Set<SysRole> sysRoles);
}
