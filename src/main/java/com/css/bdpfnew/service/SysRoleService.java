package com.css.bdpfnew.service;

import com.css.bdpfnew.model.dto.DtoSysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;

import java.util.List;
import java.util.Set;

/**
 * 角色
 * 
 * @Auther: GodNan
 * @Date: 2018/5/30 09:50
 * @Version: 1.0
 * @Description:
 */
public interface SysRoleService extends BaseService<SysRole, Long> {

	Set<SysRole> findByUuids(String[] uuids);

	void save(DtoSysRole role, Set<SysPermission> sysPermissions);

	SysRole findByUuid(String uuid);

	Set<String> findPermissionByRole(SysRole role);

	void delete(SysRole role);

	void update(DtoSysRole role, Set<SysPermission> sysPermissions);

	Set<String> findRoleByUser(SysUser voUser);

	Set<String> findRoleNameByUser(SysUser voUser);

	List<SysRole> findAll();

    SysRole findByRoleName(String name);
}
