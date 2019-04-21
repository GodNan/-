package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.dto.DtoSysUser;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.repository.SysRoleRepository;
import com.css.bdpfnew.repository.SysUserRepository;
import com.css.bdpfnew.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    public void setBaseDao(SysUserRepository userRepository) {
        super.setBaseDao(userRepository);
    }

    @Autowired
    private SysRoleRepository roleRepository;
    @Autowired
    private AreaRepository areaRepository;


    @Override
    public void save(DtoSysUser user, Set<SysRole> roles) {
        CodeAreaEntity codeAreaQx = areaRepository.findByAreaCode(user.getQxid());
        String cityName = codeAreaQx.getAreaName() ;
        CodeAreaEntity codeAreaJd = areaRepository.findByAreaCode(user.getJdid());
        if(codeAreaJd != null){
            cityName = cityName + codeAreaJd.getAreaName();
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user, sysUser);
        sysUser.setCityid(codeAreaQx.getAreaCode());
        if(codeAreaJd != null){
            sysUser.setCityid(codeAreaJd.getAreaCode());
        }
        sysUser.setRoles(roles);
        sysUser.setCityName(cityName);
        userRepository.save(sysUser);
    }

    @Override
    public void save(SysUser user) {
        userRepository.save(user);
    }

    @Override
    public SysUser find(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @Override
    public void delete(SysUser user) {
        user.setDelFlag(1);
        userRepository.save(user);
    }

    @Override
    public void update(DtoSysUser user, Set<SysRole> sysRoles) {
        CodeAreaEntity codeAreaQx = areaRepository.findByAreaCode(user.getQxid());
        String cityName = codeAreaQx.getAreaName() ;
        CodeAreaEntity codeAreaJd = areaRepository.findByAreaCode(user.getJdid());
        if(codeAreaJd != null){
            cityName = cityName + codeAreaJd.getAreaName();
        }
        SysUser sysUser = userRepository.findByUuid(user.getUuid());
        BeanUtils.copyProperties(user,sysUser);
        sysUser.setCityid(codeAreaQx.getAreaCode());
        if(codeAreaJd != null){
            sysUser.setCityid(codeAreaJd.getAreaCode());
        }
        sysUser.setCityName(cityName);
        sysUser.setRoles(sysRoles);
        userRepository.save(sysUser);
    }
}
