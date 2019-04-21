package com.css.bdpfnew.web.controller.idt;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoIdt;
import com.css.bdpfnew.service.idt.IdtNoPassedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:35:27
 * @Description:
 */
@RestController
@RequestMapping("/idtsNoPassed")
@Api(value = "残疾人评定不通过操作")
public class IdtNoPassedController {

	@Autowired
	private IdtNoPassedService idtNoPassedService;

	@PutMapping()
	@ApiOperation(value = "修改残疾人评定不通过信息", notes = "残疾人评定不通过操作")
	@RequiresPermissions("idtinfoInput")
	public Message idtNoPassedUpdate(@Valid @RequestBody DtoIdt dtoIdt) {
		try {
			if (StringUtils.isEmpty(dtoIdt.getDtocitizen().getRequestId())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = idtNoPassedService.idtNoPassedUpdate(dtoIdt);
			if (uuid == null) {
				System.out.println("未查询到相关数据");
				return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
			}
			System.out.println("提交成功");
			return Message.success("提交成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
