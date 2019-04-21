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
import com.css.bdpfnew.service.card.CardGiveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午2:47:50
 * @Description
 */

@RestController
@RequestMapping("/cardGive")
@Api(value = "卡操作")
public class CardGiveController {

	@Autowired
	private CardGiveService cardGiveService;

	@PutMapping()
	@ApiOperation(value = "发卡", notes = "发卡")
//	@RequiresPermissions("cardGive")
	public Message cardGive(@Valid @RequestBody DtoCardGiveParams dtoCardGiveParams) {
		try {
			boolean isTrue = cardGiveService.update(dtoCardGiveParams.getRequestIdCard(),dtoCardGiveParams.getCheck260(),
					dtoCardGiveParams.getCardNoFlagT(),dtoCardGiveParams.getNewCardNo());
			return Message.success("发卡成功", isTrue);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
