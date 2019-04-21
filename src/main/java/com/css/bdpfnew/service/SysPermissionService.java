package com.css.bdpfnew.service;

import com.css.bdpfnew.model.dto.DtoSysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.vo.Permission.VoSysPermissionToChild;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: GodNan
 * @Date: 2018/5/28 16:44
 * @Version: 1.0
 * @Description:
 */
public interface SysPermissionService extends BaseService<SysPermission, Long>{
    @Transactional
    void save(DtoSysPermission permission);

    SysPermission findByUuid(String parentUuid);

    Set<SysPermission> findByUuids(String[] permissionUuids);

    Set<String> findFunctionByUser(SysUser user);

    Set<String> findMenuByUser(SysUser user);

    List<Map<String,String>> findMenu();

    @Transactional
    void update(DtoSysPermission permission);

    List<SysPermission> findAll();

    /**
     * 逻辑删除(将delFlag置为1)
     * @param permission
     */
    @Transactional
    void delete(SysPermission permission);

    /**
     * 查询顶层权限
     * @return
     */
    List<SysPermission> findTopPerMission();

    /**
     * 格式化为vo数据类型
     * @param topPermission
     * @return
     */
    List<VoSysPermissionToChild> formatVo(List<SysPermission> topPermission);

    /**
     * 根据角色确认菜单
     * @param roles
     * @return
     */
    Set<String> findMenuByRoots(Set<SysRole> roles);

    /**
     * 根据角色确认权限
     * @param roles
     * @return
     */
    Set<String> findPermission(Set<SysRole> roles);
}
