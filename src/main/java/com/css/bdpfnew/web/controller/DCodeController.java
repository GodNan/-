package com.css.bdpfnew.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoDCode;
import com.css.bdpfnew.model.entity.bdpfnew.DCode;
import com.css.bdpfnew.service.DCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/codes")
@Api(value = "字典项操作")
public class DCodeController {
	@Autowired
	private DCodeService dCodeService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "获取字典项列表", notes = "获取字典项列表")
	public Message list() {
		List<DCode> dCodes = dCodeService.findAll();
		return Message.success("成功", dCodes);
	}

	@PostMapping("/getDictListByTypeCode")
	@ApiOperation(value = "字典类型查询字典项列表", notes = "通过字典类型查询字典项列表")
	public Message getDictListByTypeCode(@RequestBody DtoDCode dtoDCode) {
		List<DCode> dCodes = dCodeService.findByTypeCode(dtoDCode.getTypeCode());
		if (dCodes.isEmpty()) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
		return Message.success("成功", dCodes);
	}

	@PostMapping("/getDictListByTypeCodeWithRemark")
	@ApiOperation(value = "字典类型及标志查询字典项列表", notes = "通过字典类型查及标志询字典项列表")
	public Message getDictListByTypeCodeWithRemark(@RequestBody DtoDCode dtoDCode) {
		List<DCode> dCodes = dCodeService.findByTypeCodeAndRemark(dtoDCode.getTypeCode(), dtoDCode.getRemark());
		if (dCodes.isEmpty()) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
		return Message.success("成功", dCodes);
	}

	@RequestMapping(value = "dCode", method = RequestMethod.POST)
	@ApiOperation(value = "查询字典项列表", notes = "查询字典项列表")
	public Message list(@RequestBody DtoDCode dtoDCode) {
		List<DCode> dCodes = dCodeService.findByCodeAndDescription("%" + dtoDCode.getCode() + "%",
				"%" + dtoDCode.getDescription() + "%");
		if (dCodes.isEmpty()) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
		return Message.success("成功", dCodes);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "修改字典项", notes = "修改字典项")
	public Message update(@RequestBody DtoDCode dtoDCode) {
		dCodeService.update(dtoDCode);
		return Message.success("修改成功");
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "添加字典项", notes = "添加字典项")
	public Message save(@RequestBody DtoDCode dtoDCode) {
		dCodeService.save(dtoDCode);
		return Message.success("添加成功");
	}

	@RequestMapping(value = "{uuid}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除字典项", notes = "删除字典项")
	@ApiImplicitParam(name = "uuid", value = "字典项uuid", required = true, dataType = "String", paramType = "path")
	public Message delete(@PathVariable String uuid) {
		dCodeService.delete(uuid);
		return Message.success("删除成功");
	}

	@RequestMapping(value = "/cardStatus", method = RequestMethod.GET)
	@ApiOperation(value = "获取发证流程", notes = "获取发证所有流程")
	public Message findCardStatus() {
		List<String> cardStatus = dCodeService.findCardStatus();
		return Message.success("成功",cardStatus);
	}

}
