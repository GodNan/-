package com.css.bdpfnew.web.controller.idt;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.vo.VoIq;
import com.css.bdpfnew.service.idt.IqService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年6月30日 下午1:31:57
 * @Description:
 */
@RestController
@RequestMapping("/idts")
@Api(value = "残疾人评定操作-智力")
public class IqController {

	@Autowired
	private IqService iqService;

	@GetMapping("/iq/{requestId}")
	@ApiOperation(value = "获取残疾人视力评定信息", notes = "获取残疾人视力评定信息")
	@ApiImplicitParam(name = "requestId", value = "任务id", required = true, paramType = "path")
	public Message get(@PathVariable @NotBlank(message = "requestId不能为空") String requestId) {

		VoIq voIq = iqService.findByRequestId(requestId);
		if (voIq == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		return Message.success("成功", voIq);
	}

	@GetMapping("/iqOld/{cdpfId}")
	@ApiOperation(value = "获取残疾人肢体评定信息", notes = "获取残疾人肢体评定信息")
	@ApiImplicitParam(name = "cdpfId", value = "残疾人id", required = true, paramType = "path")
	public Message getOld(@PathVariable @NotBlank(message = "cdpfId不能为空") String cdpfId) {

		VoIq voIq = iqService.findOldByCdpfId(cdpfId);
		if (voIq == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		return Message.success("成功", voIq);
	}

}
