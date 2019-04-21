package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoAreaMove;
import com.css.bdpfnew.service.AreaMoveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年8月7日 上午9:39:48
 * @Description:
 */
@RestController
@RequestMapping("/areamoves")
@Api(value = "残疾人迁移操作")
public class AreaMoveController {

	@Autowired
	private AreaMoveService areaMoveService;

	/**
	 *  政务网迁移申请已经没有,此方法废弃
	 * @param dtoAreaMove
	 * @return
	 */
	@PostMapping("/apply")
	@ApiOperation(value = "迁移申请", notes = "户籍迁移")
	@RequiresPermissions("areamoves:apply")
	public Message save(@Valid @RequestBody DtoAreaMove dtoAreaMove) {
		try {
			String requestIdCdpfId = areaMoveService.areaMoveApply(dtoAreaMove);
			return Message.success("提交成功", requestIdCdpfId);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
