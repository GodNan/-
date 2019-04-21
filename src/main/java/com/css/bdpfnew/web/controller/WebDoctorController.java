package com.css.bdpfnew.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.css.bdpfnew.service.bdpfnet.NetDoctorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoWebDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.model.entity.bdpfnew.WebDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoWebDoctor;
import com.css.bdpfnew.service.WebDoctorService;
import com.css.bdpfnew.service.WebHospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webDoctors")
@Api(value = "医院管理")
public class WebDoctorController {

    @Autowired
	private WebDoctorService doctorService;
    @Autowired
    private NetDoctorService netDoctorService;
    @Autowired
    private WebHospitalService hospitalService;

    /**
	 * 获取所有的医院信息
	 * 
	 * @return
	 */
    @PostMapping("/listWebDoctor")
    @ApiOperation(value="医院列表", notes="医院列表")
    private Message listWebDoctor(@RequestBody PageBean pageBean) {
		Page<WebDoctor> page = doctorService.findPage(pageBean);
		return Message.success("成功", page);
	}
    
    @GetMapping(value = "{uuid}")
    @ApiOperation(value="获取单条医生信息", notes="获取单条医生信息")
    @ApiImplicitParam(name = "uuid", value = "医生uuid", required = true, paramType = "path")
  	private Message get(@PathVariable @NotBlank(message = "uuid不能为空") String uuid) {
    	WebDoctor doctor = doctorService.findByUuid(uuid);
    	if (doctor == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
    	VoWebDoctor voWebDoctor = new VoWebDoctor();
		BeanUtils.copyProperties(doctor, voWebDoctor);
		return Message.success("成功", voWebDoctor);
	}
    
    @GetMapping("/getWebHospitals")
    @ApiOperation(value="获取所在区所有医院", notes="获取所在区所有医院")
    private Message getWebHospitals() {
		List<WebHospital> webHospitals = new ArrayList<WebHospital>();
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		String areaCode = user.getCityid() + "%";
		webHospitals = hospitalService.findByAreaCode(areaCode);
        return Message.success("成功",webHospitals);
	}

    @PutMapping("/updateWebDoctor")
	@ApiOperation(value = "修改医生信息", notes = "医生信息更新")
	private Message update(@Valid @RequestBody DtoWebDoctor dtoWebDoctor) {
		try {
			if (StringUtils.isEmpty(dtoWebDoctor.getUuid())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = doctorService.update(dtoWebDoctor);
			//同步医生信息至互联网
			netDoctorService.save(dtoWebDoctor);
			return Message.success("更新成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}
    
    @PostMapping("/addWebDoctor")
	@ApiOperation(value = "新增医生", notes = "新增医生")
    private Message save(@Valid @RequestBody DtoWebDoctor dtoWebDoctor) {
		try {
			String uuid = doctorService.save(dtoWebDoctor);
			//同步医生信息至互联网
			dtoWebDoctor.setUuid(uuid);
			netDoctorService.save(dtoWebDoctor);
			return Message.success("保存成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}
    
    
    
    @GetMapping(value = "/GetHosEvalTypeArr/{workHospital}")
	@ApiOperation(value = "获取医院评残类别", notes = "获取医院评残类别")
	@ApiImplicitParam(name = "workHospital", value = "workHospital", required = true, paramType = "path")
    private Message GetHosEvalTypeArr(@PathVariable @NotBlank(message = "workHospital不能为空") String workHospital) {
		try {
			String hosEvalTypeArr = doctorService.GetHosEvalTypeArr(workHospital);
			return Message.success("成功", hosEvalTypeArr);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("失败");
		}
	}

}
