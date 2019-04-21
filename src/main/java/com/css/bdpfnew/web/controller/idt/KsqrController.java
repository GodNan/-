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
import com.css.bdpfnew.service.idt.KsqrService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author HEYCH
 * @Date 2018年11月21日 下午13:58:20
 * @Description:
 */
@RestController
@RequestMapping("/ksqr")
@Api(value = "残疾人评定操作")
public class KsqrController {

	@Autowired
	private KsqrService ksqrService;

	@PutMapping()
	@ApiOperation(value = "保存跨省迁入残疾人信息", notes = "跨省迁入")
	// TODO: 修改为 跨省迁入 的 权限
	@RequiresPermissions("idtinfoInput")
	public Message update(@Valid @RequestBody DtoIdt dtoIdt) {

		try {
			if (StringUtils.isEmpty(dtoIdt.getDtocitizen().getRequestId())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = ksqrService.update(dtoIdt);
			if(uuid == null){
				return Message.error("提交失败", Message.ERROR_CODE_EMPTY_DATA);
			}
			
			return Message.success("提交成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
