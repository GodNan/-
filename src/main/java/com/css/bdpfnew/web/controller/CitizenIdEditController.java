package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoCitizenIdEditParams;
import com.css.bdpfnew.service.CitizenIdEditService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2019年2月26日 上午10:27:40
 * @Description:
 */
@RestController
@RequestMapping("/citizenIdEidt")
@Api(value = "残疾人操作")
public class CitizenIdEditController {
	
	@Autowired
	private CitizenIdEditService citizenIdEditService;
		
	@PutMapping("/updateInfo")
	@ApiOperation(value = "修改残疾人身份证号", notes = "修改残疾人身份证号")
	public Message nameEdit(@Valid @RequestBody DtoCitizenIdEditParams dtoCitizenIdEdit) {

		try {
			if (StringUtils.isEmpty(dtoCitizenIdEdit.getCdpfId())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = citizenIdEditService.citizenIdEdit(dtoCitizenIdEdit);
			if (uuid == null) {
				return Message.error("未查询到任务数据", Message.ERROR_CODE_EMPTY_DATA);
			}
			return Message.success("提交成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}


}
