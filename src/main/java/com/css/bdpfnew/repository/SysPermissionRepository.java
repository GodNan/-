package com.css.bdpfnew.repository;


import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface SysPermissionRepository extends BaseRepository<SysPermission, Long> {

    SysPermission findByUuid(String uuid);

    Set<SysPermission> findByUuidIn(String[] permissionUuids);

    @Query(value = "select permissions.functionCode from SysUser users left outer join users.roles roles left outer join roles.permissions permissions where users.uuid = :uuid and permissions.functionCode is not null")
    Set<String> findFunctionByUserUuid(@Param("uuid") String uuid);


    @Query(value = "select permissions.menuCode from SysUser users left outer join users.roles roles left outer join roles.permissions permissions where users.uuid = :uuid")
    Set<String> findMenuByUserUuid(@Param("uuid") String uuid);


    @Query("select permission.uuid as uuid,permission.menuName as menuName from SysPermission permission where permission.functionCode is null and permission.functionName is null")
    List<Map<String,String>> findMenu();

    /**
     * update和delet语句
     * 需要添加@Modifying注解才能生效
     * @param uuid
     */
    @Modifying
    @Query("update SysPermission set delFlag = 1 where uuid = :uuid")
    void update(@Param("uuid") String uuid);

//    @Query("select permissions from SysPermission permissions where permissions.parentPermission is null")
    List<SysPermission> findAllByParentPermissionIsNull();

    @Query("select permissions.uuid as uuid from SysRole roles left outer join roles.permissions permissions where roles.uuid = :uuid ")
    Set<String> findPermissionByRoleUuid(@Param("uuid") String uuid);

    @Query("select permissions.menuCode as uuid from SysRole roles left outer join roles.permissions permissions where roles.uuid in :roleUuids and permissions.menuCode is not null")
    Set<String> findMenuByRoleUuids(@Param("roleUuids") List<String> roleUuids);

    @Query("select permissions.functionCode as uuid from SysRole roles left outer join roles.permissions permissions where roles.uuid in :roleUuids and permissions.functionCode is not null")
    Set<String> findPermissionByRoleUuids(@Param("roleUuids") List<String> roleUuids);
}
