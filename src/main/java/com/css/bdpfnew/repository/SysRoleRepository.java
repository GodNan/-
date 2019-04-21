package com.css.bdpfnew.repository;


import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SysRoleRepository extends BaseRepository<SysRole, Long> {

    Set<SysRole> findByUuidIn(String[] uuids);

    SysRole findByUuid(String uuid);

    @Query("select roles.uuid as uuid from SysUser users left outer join users.roles roles where users.uuid = :uuid ")
    Set<String> findRoleByUserUuid(@Param("uuid") String uuid);
    
    @Query("select roles.roleName as uuid from SysUser users left outer join users.roles roles where users.uuid = :uuid ")
    Set<String> findRoleNameByUserUuid(@Param("uuid") String uuid);

    SysRole findByRoleName(String name);

}
