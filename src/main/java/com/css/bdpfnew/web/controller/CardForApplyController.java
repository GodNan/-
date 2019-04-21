package com.css.bdpfnew.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForApplys;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.service.CardForApplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年11月14日 上午10:27:40
 * @Description:
 */
@RestController
@RequestMapping("/cardForApply")
@Api(value = "残疾人操作")
public class CardForApplyController {

	@Autowired
	private CardForApplyService cardForApplyService;

	@PostMapping("/list")
	@ApiOperation(value = "业务办理列表", notes = "业务办理列表")
	@RequiresPermissions(value = { "zhenghuanka" })
	public Message listCitizens(@RequestBody PageBean pageBean) {
		Page<CardForApplys> page = cardForApplyService.findPage(pageBean);
		return Message.success("成功", page);
	}

}
