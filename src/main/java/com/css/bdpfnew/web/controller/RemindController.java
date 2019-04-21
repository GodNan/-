package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.service.card.CardForChangesService;
import com.css.bdpfnew.service.card.CardForDelaysService;
import com.css.bdpfnew.service.card.CardForGivesService;
import com.css.bdpfnew.service.card.CardForPhotosService;
import com.css.bdpfnew.service.task.TasksTodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午9:52:07
 * @Description:
 */

@RestController
@RequestMapping("/reminds")
@Api(value = "提醒操作")
public class RemindController {

	@Autowired
	private TasksTodoService tasksTodoService;

	@Autowired
	private CardForPhotosService cardForPhotosService;

	@Autowired
	private CardForChangesService cardForChangesService;

	@Autowired
	private CardForDelaysService cardForDelaysService;

	@Autowired
	private CardForGivesService cardForGivesService;

	@PostMapping("/tasksTodo")
	@ApiOperation(value = "待办任务提醒", notes = "待办任务提醒")
	@RequiresPermissions(value = { "daiban" })
	public Message tasksTodo(@Valid @RequestBody DtoRequest dtoRequest) {
		Integer countOfTasksTodo = tasksTodoService.getCountOfTasksToDo(dtoRequest);
		return Message.success("获取成功", countOfTasksTodo);
	}

	@PostMapping("/cardForPhotos")
	@ApiOperation(value = "卡面照片处理提醒", notes = "卡面照片处理提醒")
	@RequiresPermissions(value = { "zhaopian" })
	public Message cardForPhotos(@Valid @RequestBody DtoRequest dtoRequest) {
		Integer countOfCardForPhotos = cardForPhotosService.getCountOfCardForPhotos(dtoRequest);
		return Message.success("获取成功", countOfCardForPhotos);
	}

	@PostMapping("/cardForChanges")
	@ApiOperation(value = "待补换卡提醒", notes = "待补换卡提醒")
	@RequiresPermissions(value = { "daibuban" })
	public Message cardForChanges(@Valid @RequestBody DtoRequest dtoRequest) {
		Integer countOfcardForChanges = cardForChangesService.getCountOfCardForChanges(dtoRequest);
		return Message.success("获取成功", countOfcardForChanges);
	}

	@PostMapping("/cardForDelays")
	@ApiOperation(value = "公交延期功能提醒", notes = "公交延期功能提醒")
	@RequiresPermissions(value = { "gongjiao" })
	public Message cardForDelays(@Valid @RequestBody DtoRequest dtoRequest) {
		Integer countOfcardForDelays = cardForDelaysService.getCountOfCardForDelays(dtoRequest);
		return Message.success("获取成功", countOfcardForDelays);
	}

	@PostMapping("/cardForGives")
	@ApiOperation(value = "待发证提醒", notes = "待发证提醒")
	@RequiresPermissions(value = { "daifazheng" })
	public Message cardForGives(@Valid @RequestBody DtoRequest dtoRequest) {
		Integer countOfcardForGives = cardForGivesService.getCountOfCardForGives(dtoRequest);
		return Message.success("获取成功", countOfcardForGives);
	}
}
