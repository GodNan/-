package com.css.bdpfnew.web.controller.idt;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.vo.VoIdtResult;
import com.css.bdpfnew.service.idt.IdtResultService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年8月1日 下午3:35:58
 * @Description:
 */
@RestController
@RequestMapping("/idts")
@Api(value = "残疾人评定操作-评定结果")
public class IdtResultController {

	@Autowired
	private IdtResultService idtResultService;

	@GetMapping("/idtResult/{requestId}")
	@ApiOperation(value = "获取残疾人评定信息", notes = "获取残疾人评定信息")
	@ApiImplicitParam(name = "requestId", value = "任务id", required = true, paramType = "path")
	public Message get(@PathVariable @NotBlank(message = "requestId不能为空") String requestId) {

		VoIdtResult voIdtResult = idtResultService.findByRequestId(requestId);
		if (voIdtResult == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		return Message.success("成功", voIdtResult);
	}

}
