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
import com.css.bdpfnew.service.idt.IdtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:35:27
 * @Description:
 */
@RestController
@RequestMapping("/idts")
@Api(value = "残疾人评定操作")
public class IdtController {

	@Autowired
	private IdtService idtService;

	@PutMapping()
	@ApiOperation(value = "修改残疾人评定信息", notes = "受理")
	@RequiresPermissions("idtinfoInput")
	public Message update(@Valid @RequestBody DtoIdt dtoIdt) {

		System.out.println("IdtController -- update");
		try {
			if (StringUtils.isEmpty(dtoIdt.getDtocitizen().getRequestId())) {
				
				System.out.println("uuid不能为空");
				
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = idtService.update(dtoIdt);
			if(uuid == null){
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
