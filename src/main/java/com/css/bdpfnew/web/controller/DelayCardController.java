/**
 * 
 */
package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoCardGiveParams;
import com.css.bdpfnew.service.card.DelayCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午2:47:50
 * @Description
 */

@RestController
@RequestMapping("/cardDelay")
@Api(value = "卡延期")
public class DelayCardController {

	@Autowired
	private DelayCardService delayCardService;

	@PutMapping()
	@ApiOperation(value = "卡延期", notes = "卡延期")
	@RequiresPermissions("cardDelay")
	public Message cardGive(@Valid @RequestBody DtoCardGiveParams dtoCardGiveParams) {
		try {
			boolean isTrue = delayCardService.update(dtoCardGiveParams.getRequestIdCard());
			return Message.success("延期成功", isTrue);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
