package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoNameEditParams;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.vo.VoCitizen;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.service.NameEditService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2019年1月27日 上午10:27:40
 * @Description:
 */
@RestController
@RequestMapping("/nameEidt")
@Api(value = "残疾人操作")
public class NameEditController {
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private NameEditService nameEditService;

	@GetMapping(value = "/getCitizenData/{citizenId}")
	@ApiOperation(value = "获取残疾人信息", notes = "获取残疾人信息")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	public Message getCitizenData(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId){
		CdpfCitizen citizen = citizenRepository.findByCitizenId(citizenId);
		if (citizen == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		VoCitizen voCitizen = new VoCitizen();
		BeanUtils.copyProperties(citizen, voCitizen);

		return Message.success("成功", voCitizen);
	}
		
	@PutMapping("/updateInfo")
	@ApiOperation(value = "修改残疾人姓名", notes = "修改残疾人姓名")
	public Message nameEdit(@Valid @RequestBody DtoNameEditParams dtoNameEdit) {

		try {
			if (StringUtils.isEmpty(dtoNameEdit.getCdpfId())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = nameEditService.nameEdit(dtoNameEdit);
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
