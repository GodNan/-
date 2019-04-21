package com.css.bdpfnew.repository;


import com.css.bdpfnew.model.entity.bdpfnew.SysUser;


public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    SysUser findByUuid(String uuid);


}
