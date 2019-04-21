package com.css.bdpfnew.web.controller;

import java.util.*;

import javax.validation.Valid;

import com.css.bdpfnew.constant.CommonConfig;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoLogin;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.vo.Permission.VoLoginInfo;
import com.css.bdpfnew.service.LoginService;
import com.css.bdpfnew.service.SysPermissionService;
import com.css.bdpfnew.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @description: 登录相关Controller
 * @date: 2017/10/24 10:33
 */
@RestController
@RequestMapping("/login")
@Api(value = "登录操作")
public class LoginController {

	@Autowired
	private CommonConfig commonConfig;

	@Autowired
	private SysPermissionService sysPermissionService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 登录
	 * 
	 * @param login
	 * @return
	 */
	@PostMapping("/auth")
	public Message authLogin(@Valid @RequestBody DtoLogin login) {
		String sessionId = null;
		try {
			sessionId = loginService.authLogin(login.getUsername(), login.getPassword());
		} catch (UnknownAccountException uae) {
			return Message.error("用户名密码错误", Message.ERROR_CODE_AUTHENTICATE);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		return Message.success("登录成功", map);
	}

	/**
	 * 查询当前登录用户的信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/info/{timestamp}", method = RequestMethod.GET)
	@ApiOperation(value = "获取登录人详细信息", notes = "获取登录人详细信息")
	public Message getInfo() {
		SysUser user = loginService.findUserBySession();
		VoLoginInfo info = new VoLoginInfo();
		BeanUtils.copyProperties(user, info);

		Set<String> menu = new HashSet<String>();
		Set<String> permission = new HashSet<String>();
		Set<String> roleList = new HashSet<String>();
		if(commonConfig.isDev){
			// 正常登录所需代码块
			menu = sysPermissionService.findMenuByUser(user);
			permission = sysPermissionService.findFunctionByUser(user);
			roleList = sysRoleService.findRoleNameByUser(user);
		}else {
			// CA登录所需代码块
			menu = sysPermissionService.findMenuByRoots(user.getRoles());
			permission = sysPermissionService.findPermission(user.getRoles());
			for (SysRole sysRole : user.getRoles()) {
				roleList.add(sysRole.getUuid());
			}
		}
		info.setMenuList(menu);
		info.setPermissionList(permission);
		// TODO: 角色信息
		SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_ROLE_INFO, roleList);
		info.setRoleList(roleList);
		return Message.success("成功", info);
	}

	/**
	 * 登出
	 *
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ApiOperation(value = "登出", notes = "登出")
	public Message logout() {
		// 从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		SysUser userInfo = (SysUser) session.getAttribute(Constants.SESSION_USER_INFO);
		return Message.success("成功");
	}
}
