package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoCdpfZhuxiao;
import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenForZhuxiaos;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.service.CitizenForZhuxiaoService;
import com.css.bdpfnew.service.ZhuxiaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年11月14日 上午10:27:40
 * @Description:
 */
@RestController
@RequestMapping("/citizenForZhuxiao")
@Api(value = "残疾人操作")
public class CitizenForZhuxiaoController {

	@Autowired
	private CitizenForZhuxiaoService citizenForZhuxiaoService;
	@Autowired
	private ZhuxiaoService zhuxiaoService;

	@PostMapping("/list")
	@ApiOperation(value = "业务办理列表", notes = "业务办理列表")
	@RequiresPermissions(value = { "daizhuxiao" })
	public Message listCitizens(@RequestBody PageBean pageBean) {
		Page<CitizenForZhuxiaos> page = citizenForZhuxiaoService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/noLogout")
	@ApiOperation(value = "不注销", notes = "不注销")
	@RequiresPermissions(value = { "daizhuxiao" })
	public Message noLogout(@Valid @RequestBody DtoCdpfZhuxiao dtoCdpfZhuxiao) {
		try {
			String uuid = zhuxiaoService.noLogout(dtoCdpfZhuxiao);
			return Message.success("操作成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	@PostMapping("/lock")
	@ApiOperation(value = "冻结", notes = "冻结")
	@RequiresPermissions(value = { "daizhuxiao" })
	public Message lock(@Valid @RequestBody DtoCdpfZhuxiao dtoCdpfZhuxiao) {
		try {
			String uuid = zhuxiaoService.lock(dtoCdpfZhuxiao);
			if (uuid == null) {
				return Message.error("未查询到残疾人数据", Message.ERROR_CODE_EMPTY_DATA);
			}
			return Message.success("操作成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	@PostMapping("/unlock")
	@ApiOperation(value = "解冻", notes = "解冻")
	@RequiresPermissions(value = { "daizhuxiao" })
	public Message unlock(@Valid @RequestBody DtoCdpfZhuxiao dtoCdpfZhuxiao) {
		try {
			String uuid = zhuxiaoService.unlock(dtoCdpfZhuxiao);
			return Message.success("操作成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
