package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.bdpfnew.Message;
import com.css.bdpfnew.model.dto.DtoRequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.service.task.RequestNoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年7月6日 上午11:08:50
 * @Description:
 */
@RestController
@RequestMapping("/requestnotes")
@Api(value = "任务记录操作")
public class RequestNoteController {

	@Autowired
	private RequestNoteService requestNoteService;

	@PostMapping("/list")
	@ApiOperation(value = "任务记录列表", notes = "任务记录列表")
	public Message listCitizens(@RequestBody PageBean pageBean) {
		Page<RequestNote> page = requestNoteService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping()
	@ApiOperation(value = "新增任务记录", notes = "任务处理")
	public Message save(@Valid @RequestBody DtoRequestNote dtoRequestNote) {

		try {

			String uuid = requestNoteService.save(dtoRequestNote);
			return Message.success("保存成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

}
