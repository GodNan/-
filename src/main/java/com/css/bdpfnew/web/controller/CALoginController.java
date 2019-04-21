package com.css.bdpfnew.web.controller;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.SysRole;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.service.LoginService;
import com.css.bdpfnew.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/ca")
@Api(value = "CA登录操作")
public class CALoginController {

	// 日志信息
	private static Log log = LogFactory.getLog(CALoginController.class);

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/logintest", method = RequestMethod.GET)
	@ApiOperation(value="ca登录方法", notes="ca登录方法")
	public String logintest(ModelMap map) {
		map.addAttribute("tockionId","110119101001");
		return "/test";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ApiOperation(value="ca登录方法", notes="ca登录方法")
	public String login(HttpServletRequest request, ModelMap map) throws UnsupportedEncodingException {
		String bjca_ticket = request.getParameter("BJCA_TICKET");
		//区划
		String departWEBAddress = request.getParameter("departWEBAddress");
		String bjca_ticket_type = request.getParameter("BJCA_TICKET_TYPE");
		String bjca_server_cert = request.getParameter("BJCA_SERVER_CERT");
//		TicketManager ticketmag = new TicketManager();
//		UserTicket userticket = ticketmag.getTicket(bjca_ticket, bjca_ticket_type, bjca_server_cert);

		SysUser sysUser = new SysUser();
		Document document = null;
		try {
			document = DocumentHelper.parseText(bjca_ticket);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List list = document.selectNodes("//role-infos/role-info");
		Element info = (Element)document.selectSingleNode("//append-info");
		Element baseInfo = (Element)document.selectSingleNode("//base-info");
		// 设置用户基本信息
		sysUser.setUuid(baseInfo.element("UserID").getStringValue());
		sysUser.setUsername(info.element("userLoginName").getStringValue());
		sysUser.setNickname(info.element("userName").getStringValue());
		sysUser.setCityName(info.element("departName").getStringValue());
		sysUser.setCityid(departWEBAddress);
		Element root = document.getRootElement();
		Element element = root.element("role-infos");//获取子节点
		// 获取角色信息
		for (int i=0; i < list.size(); i++) {
			Element data = (Element)list.get(i);
			String RoleName= data.element("RoleName").getStringValue();//获取子节点属性的值
			String RoleCode= data.element("RoleCode").getStringValue();//获取子节点属性的值
			SysRole role = sysRoleService.findByRoleName(RoleCode);
			sysUser.getRoles().add(role);
		}
		String sessionId = loginService.authLogin(info.element("userLoginName").getStringValue());
		SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, sysUser);
		map.addAttribute("tockionId",sessionId);
		return "/test";
	}
}
