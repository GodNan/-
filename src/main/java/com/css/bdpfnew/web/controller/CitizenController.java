package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import com.css.bdpfnew.model.entity.bdpfnew.IdtPhotosReup;
import com.css.bdpfnew.model.page.Filter;
import com.css.bdpfnew.service.idt.IdtPhotoReupService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;
import com.css.bdpfnew.model.entity.bdpfnew.GonganData;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoCitizen;
import com.css.bdpfnew.model.vo.VoGonganData;
import com.css.bdpfnew.service.CitizenNetService;
import com.css.bdpfnew.service.CitizenService;
import com.css.bdpfnew.service.GonganDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年6月22日 上午10:27:40
 * @Description:
 */
@RestController
@RequestMapping("/citizens")
@Api(value = "残疾人操作")
public class CitizenController {

	@Autowired
	private CitizenService citizenService;

	@Autowired
	private CitizenNetService citizenNetService;

	@Autowired
	private GonganDataService gonganDataService;
	@Autowired
	private IdtPhotoReupService idtPhotoReupService;

	@PostMapping("/list")
	@ApiOperation(value = "业务办理列表", notes = "业务办理列表")
	@RequiresPermissions(value = { "businessList" })
	public Message listCitizens(@RequestBody PageBean pageBean) {
		pageBean.getFilters().add(Filter.ne("statusRecord",888));
		pageBean.getFilters().add(Filter.ne("hadFinalReviewed",0));
		Page<CdpfCitizen> page = citizenService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/photoList")
	@ApiOperation(value = "评残照片补传列表",notes = "评残照片补传列表")
	public Message oldPhotosReupList(@RequestBody PageBean pageBean){
		Page<IdtPhotosReup> page=idtPhotoReupService.findPage(pageBean);
		return Message.success("成功",page);
	}

	@GetMapping(value = "/net/{uuid}")
	@ApiOperation(value = "获取互联网任务残疾人信息", notes = "获取互联网任务残疾人信息")
	@ApiImplicitParam(name = "uuid", value = "残疾人uuid", required = true, paramType = "path")
	public Message getNet(@PathVariable @NotBlank(message = "uuid不能为空") String uuid) {
		CdpfCitizenNet citizen = citizenNetService.findByUuid(uuid);
		if (citizen == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		VoCitizen voCitizen = new VoCitizen();
		BeanUtils.copyProperties(citizen, voCitizen);

		return Message.success("成功", voCitizen);
	}

	@GetMapping(value = "/gongan/{citizenId}")
	@ApiOperation(value = "获取公安局信息", notes = "获取公安局信息")
	@ApiImplicitParam(name = "citizenId", value = "残疾人citizenId", required = true, paramType = "path")
	public Message getGonganData(@PathVariable @NotBlank(message = "citizenId不能为空") String citizenId) {

		GonganData gongan = gonganDataService.findByCitizenId(citizenId);
		if (gongan == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		VoGonganData voGonganData = new VoGonganData();
		BeanUtils.copyProperties(gongan, voGonganData);

		return Message.success("成功", voGonganData);
	}

	@GetMapping(value = "{uuid}")
	@ApiOperation(value = "获取残疾人信息", notes = "获取残疾人信息")
	@ApiImplicitParam(name = "uuid", value = "残疾人uuid", required = true, paramType = "path")
	public Message get(@PathVariable @NotBlank(message = "uuid不能为空") String uuid) {
		CdpfCitizen citizen = citizenService.findByUuid(uuid);
		if (citizen == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}

		VoCitizen voCitizen = new VoCitizen();
		BeanUtils.copyProperties(citizen, voCitizen);

		return Message.success("成功", voCitizen);
	}

	@PostMapping()
	@ApiOperation(value = "新增残疾人", notes = "新证申请")
	public Message save(@Valid @RequestBody DtoCitizen dtoCitizen) {
		try {
			String requestIdCdpfId = citizenService.save(dtoCitizen);
			return Message.success("保存成功", requestIdCdpfId);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	@PutMapping()
	@ApiOperation(value = "修改残疾人基本信息", notes = "基本资料更新")
	public Message update(@Valid @RequestBody DtoCitizen dtoCitizen) {

		try {
			if (StringUtils.isEmpty(dtoCitizen.getUuid())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = citizenService.update(dtoCitizen);
			return Message.success("更新成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
