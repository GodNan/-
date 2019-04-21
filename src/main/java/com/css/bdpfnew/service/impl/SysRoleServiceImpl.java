package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.dto.DtoSysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.SysPermissionRepository;
import com.css.bdpfnew.repository.SysRoleRepository;
import com.css.bdpfnew.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 角色
 * @Auther: GodNan
 * @Date: 2018/5/30 09:50
 * @Version: 1.0
 * @Description:
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService{
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    public void setBaseDao(SysRoleRepository sysRoleRepository) {
        super.setBaseDao(sysRoleRepository);
    }

    @Autowired
    private SysPermissionRepository sysPermissionRepository;


    @Override
    public Set<SysRole> findByUuids(String[] uuids) {
        return sysRoleRepository.findByUuidIn(uuids);

    }

    @Override
    @Transactional
    public void save(DtoSysRole role, Set<SysPermission> sysPermissions) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(role, sysRole);
        sysRole.setPermissions(sysPermissions);
        sysRoleRepository.save(sysRole);
    }

    @Override
    public SysRole findByUuid(String uuid) {
        return sysRoleRepository.findByUuid(uuid);
    }

    @Override
    public Set<String> findPermissionByRole(SysRole role) {
        Set<String> permissions = sysPermissionRepository.findPermissionByRoleUuid(role.getUuid());
        permissions.remove(null);
        return permissions;
    }

    @Override
    public void delete(SysRole role) {
        role.setDelFlag(1);
        sysRoleRepository.save(role);
    }

    @Override
    public void update(DtoSysRole role, Set<SysPermission> sysPermissions) {
        SysRole sysRole = sysRoleRepository.findByUuid(role.getUuid());
        BeanUtils.copyProperties(role,sysRole);
        sysRole.setPermissions(sysPermissions);
        sysRoleRepository.save(sysRole);
    }

    @Override
    public Set<String> findRoleByUser(SysUser user) {
        Set<String> roles = sysRoleRepository.findRoleByUserUuid(user.getUuid());
        roles.remove(null);
        return roles;
    }
    
    @Override
    public Set<String> findRoleNameByUser(SysUser user) {
        Set<String> roles = sysRoleRepository.findRoleNameByUserUuid(user.getUuid());
        roles.remove(null);
        return roles;
    }

    @Override
    public List<SysRole> findAll() {
        List<SysRole> roles = sysRoleRepository.findAll();
        return roles;
    }

    @Override
    public SysRole findByRoleName(String name) {
        return sysRoleRepository.findByRoleName(name);
    }
}
