package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.dto.DtoSysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysPermission;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.vo.Permission.VoSysPermissionToChild;
import com.css.bdpfnew.repository.SysPermissionRepository;
import com.css.bdpfnew.service.SysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限
 *
 * @Auther: GodNan
 * @Date: 2018/5/28 16:44
 * @Version: 1.0
 * @Description:
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, Long> implements SysPermissionService {

	@Autowired
	private SysPermissionRepository sysPermissionRepository;

	@Autowired
	public void setBaseDao(SysPermissionRepository sysPermissionRepository) {
		super.setBaseDao(sysPermissionRepository);
	}

	@Override
	@Transactional
	public void save(DtoSysPermission permission) {
		SysPermission sysPermission = new SysPermission();
		BeanUtils.copyProperties(permission, sysPermission);
		if (StringUtils.isNotEmpty(permission.getParentUuid())) {
			SysPermission parentPermission = sysPermissionRepository.findByUuid(permission.getParentUuid());
			sysPermission.setParentPermission(parentPermission);
		}
		sysPermissionRepository.save(sysPermission);
	}

	@Override
	public SysPermission findByUuid(String uuid) {
		return sysPermissionRepository.findByUuid(uuid);
	}

	@Override
	public Set<SysPermission> findByUuids(String[] permissionUuids) {
		return sysPermissionRepository.findByUuidIn(permissionUuids);
	}

	@Override
	public Set<String> findFunctionByUser(SysUser user) {
		return sysPermissionRepository.findFunctionByUserUuid(user.getUuid()).stream().filter((String s) -> s != null)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> findMenuByUser(SysUser user) {
		return sysPermissionRepository.findMenuByUserUuid(user.getUuid()).stream().filter((String s) -> s != null)
				.collect(Collectors.toSet());
	}

	@Override
	public List<Map<String, String>> findMenu() {
		List<Map<String, String>> list = sysPermissionRepository.findMenu();
		return list;
	}

	@Override
	public void update(DtoSysPermission permission) {
		SysPermission sysPermission = sysPermissionRepository.findByUuid(permission.getUuid());
		BeanUtils.copyProperties(permission, sysPermission, new String[] { "uuid" });
		if (StringUtils.isNotEmpty(permission.getParentUuid())) {
			SysPermission parentPermission = sysPermissionRepository.findByUuid(permission.getParentUuid());
			sysPermission.setParentPermission(parentPermission);
		}
		sysPermissionRepository.save(sysPermission);
	}

	@Override
	@Cacheable(value = "sysPermissionAll",keyGenerator="keyGenerator")
	public List<SysPermission> findAll() {
		return sysPermissionRepository.findAll();
	}

	@Override
	public void delete(SysPermission permission) {
		permission.setDelFlag(1);
		sysPermissionRepository.save(permission);
	}

	@Override
	public List<SysPermission> findTopPerMission() {
		List<SysPermission> permissions = sysPermissionRepository.findAllByParentPermissionIsNull();
		return permissions;
	}

	@Override
	public List<VoSysPermissionToChild> formatVo(List<SysPermission> topPermission) {
		ArrayList<VoSysPermissionToChild> voSysPermissionToChilds = new ArrayList<>();
		for (SysPermission permission : topPermission) {
			VoSysPermissionToChild voPermission = new VoSysPermissionToChild();
			BeanUtils.copyProperties(permission, voPermission);
			List<SysPermission> childPermissions = permission.getChildPermissions();
			voPermission.setChildList(getChild(childPermissions));
			voSysPermissionToChilds.add(voPermission);
		}
		return voSysPermissionToChilds;
	}

	@Override
	public Set<String> findMenuByRoots(Set<SysRole> roles) {
		List<String> roleUuids = new ArrayList<>();
		for (SysRole sysRole : roles) {
			roleUuids.add(sysRole.getUuid());
		}
		Set<String> permissionMenu = sysPermissionRepository.findMenuByRoleUuids(roleUuids);
		return permissionMenu;
	}

	@Override
	public Set<String> findPermission(Set<SysRole> roles) {
		List<String> roleUuids = new ArrayList<>();
		for (SysRole sysRole : roles) {
			roleUuids.add(sysRole.getUuid());
		}
		Set<String> permission = sysPermissionRepository.findPermissionByRoleUuids(roleUuids);
		return permission;
	}

	/**
	 * 递归生成菜单
	 *
	 * @param childPermissions
	 * @return
	 */
	private List<VoSysPermissionToChild> getChild(List<SysPermission> childPermissions) {
		ArrayList<VoSysPermissionToChild> voSysPermissions = new ArrayList<VoSysPermissionToChild>();
		for (SysPermission permission : childPermissions) {
			VoSysPermissionToChild child = new VoSysPermissionToChild();
			BeanUtils.copyProperties(permission, child);
			voSysPermissions.add(child);
			if (permission.getChildPermissions() == null) {
				return null;
			} else {
				child.setChildList(getChild(permission.getChildPermissions()));
			}
		}
		return voSysPermissions;
	}
}
