/**
 * 
 */
package com.css.bdpfnew.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCardLog;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForChanges;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForDelays;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForGives;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForPhotos;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoCdpfCard;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.card.CardForChangesService;
import com.css.bdpfnew.service.card.CardForDelaysService;
import com.css.bdpfnew.service.card.CardForGivesService;
import com.css.bdpfnew.service.card.CardForPhotosService;
import com.css.bdpfnew.service.card.CardLogService;
import com.css.bdpfnew.service.card.CardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午2:47:50
 * @Description
 */

@RestController
@RequestMapping("/cards")
@Api(value = "卡操作")
public class CardController {

	@Autowired
	private CardService cardService;
	@Autowired
	private CardLogService cardLogService;
	@Autowired
	private CardForPhotosService cardForPhotosService;
	@Autowired
	private CardForChangesService cardForChangesService;
	@Autowired
	private CardForDelaysService cardForDelaysService;

	@Autowired
	private CardForGivesService cardForGivesService;
	
	@Autowired
	private CdpfCardRepository cdpfCardRepository;

	@PostMapping("/cardForMakings")
	@ApiOperation(value = "制证跟踪", notes = "制证跟踪")
	@RequiresPermissions(value = { "cardForMakings:list" })
	public Message cardForMakings(@RequestBody PageBean pageBean) {
		Page<CdpfCard> page = cardService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/cardForPhotos")
	@ApiOperation(value = "卡面照片处理", notes = "卡面照片处理")
	// @RequiresPermissions(value={"cards:cardForPhotos"})
	@RequiresPermissions(value = { "zhaopian" })
	public Message cardForPhotos(@RequestBody PageBean pageBean) {
		Page<CardForPhotos> page = cardForPhotosService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/cardForChanges")
	@ApiOperation(value = "待补换卡", notes = "待补换卡")
	// @RequiresPermissions(value={"cards:cardForChanges"})
	@RequiresPermissions(value = { "daibuban" })
	public Message cardForChanges(@RequestBody PageBean pageBean) {
		Page<CardForChanges> page = cardForChangesService.findPage(pageBean);
		return Message.success("成功", page);
	}
	
	@PostMapping("/cardForDelays")
	@ApiOperation(value = "待卡延期", notes = "待卡延期")
	// @RequiresPermissions(value={"cards:cardForDelays"})
	@RequiresPermissions(value = { "gongjiao" })
	public Message cardForDelays(@RequestBody PageBean pageBean) {
		Page<CardForDelays> page = cardForDelaysService.findPage(pageBean);
		return Message.success("成功", page);
	}
	
	@PostMapping("/cardForGives")
	@ApiOperation(value = "待发证", notes = "待发证")
	// @RequiresPermissions(value={"cards:cardForGives"})
	@RequiresPermissions(value = { "daifazheng" })
	public Message cardForGives(@RequestBody PageBean pageBean) {
		Page<CardForGives> page = cardForGivesService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/cardLogs")
	@ApiOperation(value = "证制作历史", notes = "证制作历史")
	// @RequiresPermissions(value={"cards:cardLogs"})
	@RequiresPermissions(value = { "cardForMakings:list" })
	public Message cardLogs(@RequestBody PageBean pageBean) {
		Page<CdpfCardLog> page = cardLogService.findPage(pageBean);
		return Message.success("成功", page);
	}
	
	@GetMapping(value = "{requestId}")
	@ApiOperation(value = "获取卡信息", notes = "获取卡信息")
	@ApiImplicitParam(name = "requestId", value = "requestId", required = true, paramType = "path")
	public Message get(@PathVariable @NotBlank(message = "requestId不能为空") String requestId) {
		CdpfCard card = cdpfCardRepository.findByRequestId(requestId);
		if (card == null) {
			return Message.error("未查询到相关数据", Message.ERROR_CODE_EMPTY_DATA);
		}
		VoCdpfCard voCdpfCard = new VoCdpfCard();
		BeanUtils.copyProperties(card, voCdpfCard);
		return Message.success("成功", voCdpfCard);
	}

}
