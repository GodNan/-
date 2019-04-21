package com.css.bdpfnew.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfLog;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.service.CdpfLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年12月20日
 * @Description: 任务操作
 */
@RestController
@RequestMapping("/cdpflog")
@Api(value = "任务操作")
public class CdpfLogController {

	@Autowired
	private CdpfLogService cdpfLogService;

	@PostMapping("/operLog")
	@ApiOperation(value = "操作历史", notes = "操作历史")
	public Message tasksLog(@RequestBody PageBean pageBean) {
		//根据edittime排序，降序
		pageBean.setOrderProperty("edittime");
		Page<CdpfLog> page = cdpfLogService.findPage(pageBean);
		return Message.success("成功", page);
	}

}
