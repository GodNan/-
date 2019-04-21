package com.css.bdpfnew.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoState;
import com.css.bdpfnew.service.StateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年7月5日 下午5:03:49
 * @Description:
 */
@RestController
@RequestMapping("/states")
@Api(value = "流程状态操作")
public class StateController {

	@Autowired
	private StateService stateService;

	@PostMapping("/processSteps")
	@ApiOperation(value = "流程状态列表", notes = "流程状态列表")
	public Message processSteps(@RequestBody DtoState dtoState) {
		return Message.success("成功", stateService.getProcessSteps(dtoState));
	}

	@PostMapping("/processBackSteps")
	@ApiOperation(value = "流程回退状态列表", notes = "流程回退状态列表")
	public Message processBackSteps(@RequestBody DtoState dtoState) {
		return Message.success("成功", stateService.getProcessBackSteps(dtoState));
	}

	@PostMapping("/backStepsByOrderNum")
	@ApiOperation(value = "流程回退状态列表by排序号", notes = "流程回退状态列表by排序号")
	public Message backStepsByOrderNum(@RequestBody DtoState dtoState) {
		return Message.success("成功", stateService.getBackStepsByOrderNum(dtoState));
	}
}
