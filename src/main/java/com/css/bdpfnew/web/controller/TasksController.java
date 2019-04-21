package com.css.bdpfnew.web.controller;

import javax.validation.Valid;

import com.css.bdpfnew.model.page.Order;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotBlank;
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
import com.css.bdpfnew.model.dto.DtoNewTask;
import com.css.bdpfnew.model.dto.DtoPost;
import com.css.bdpfnew.model.dto.DtoTask;
import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.view.TasksAll;
import com.css.bdpfnew.model.entity.bdpfnew.view.TasksDid;
import com.css.bdpfnew.model.entity.bdpfnew.view.TasksTodo;
import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.VoRequest;
import com.css.bdpfnew.model.vo.VoTask;
import com.css.bdpfnew.service.task.HistoryService;
import com.css.bdpfnew.service.task.RequestNoteService;
import com.css.bdpfnew.service.task.RequestService;
import com.css.bdpfnew.service.task.TasksAllService;
import com.css.bdpfnew.service.task.TasksDidService;
import com.css.bdpfnew.service.task.TasksDoneService;
import com.css.bdpfnew.service.task.TasksTodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @Author erDuo
 * @Date 2018年6月15日 上午11:16:52
 * @Description: 任务操作
 */
@RestController
@RequestMapping("/tasks")
@Api(value = "任务操作")
public class TasksController {

	@Autowired
	private TasksTodoService tasksTodoService;
	@Autowired
	private TasksDidService tasksDidService;
	@Autowired
	private TasksDoneService tasksDoneService;
	@Autowired
	private TasksAllService tasksAllService;
	@Autowired
	private RequestService requestService;
	@Autowired
	private RequestNoteService requestNoteService;
	@Autowired
	private HistoryService historyService;

	@PostMapping("/tasksTodo")
	@ApiOperation(value = "待办任务", notes = "待办任务")
	// @RequiresPermissions(value={"tasks:tasksTodo"})
	@RequiresPermissions(value = { "daiban" })
	public Message tasksTodo(@RequestBody PageBean pageBean) {
		Page<TasksTodo> page = tasksTodoService.findPage(pageBean);
		return Message.success("成功", page);
	}

	// TODO:
	// 所有新建任务的入口
	@PostMapping("/newTask")
	@ApiOperation(value = "新任务申请", notes = "新任务申请")
	public Message newTask(@Valid @RequestBody DtoNewTask dtoNewTask) {

		try {
			if (StringUtils.isEmpty(dtoNewTask.getCdpfId())) {
				return Message.error("请选择残疾人", Message.ERROR_CODE_PARAM);
			}
			String uuid = tasksTodoService.newTask(dtoNewTask);
			if (uuid == null) {
				return Message.error("未查询到残疾人", Message.ERROR_CODE_EMPTY_DATA);
			}
			return Message.success("提交成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

	@PutMapping("/handleTaskTodo")
	@ApiOperation(value = "修改残疾人评定信息", notes = "受理")
	public Message handleTaskTodo(@Valid @RequestBody DtoTask dtoTask) {

		try {
			if (StringUtils.isEmpty(dtoTask.getRequestNote().getRequestId())) {
				return Message.error("uuid不能为空", Message.ERROR_CODE_PARAM);
			}
			String uuid = tasksTodoService.handleTaskTodo(dtoTask);
			if (uuid == null) {
				return Message.error("未查询到任务数据", Message.ERROR_CODE_EMPTY_DATA);
			}
			
			if (uuid.length() == 10) {
				return Message.error("此人一年内做过注销,需要在" + uuid + "之后进行申请,可以选择不通过" , Message.ERROR_ZHUXIAO_NEXT_APPLY_DATE);
			}
			
			return Message.success("提交成功", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
		}
	}

//	@PutMapping("/handleTaskTodoPost")
//	@ApiOperation(value = "公示处理", notes = "公示处理")
//	public Message handleTaskTodoPost(@Valid @RequestBody DtoPost dtoPost) {
//
//		try {
//			if (StringUtils.isEmpty(dtoPost.getRequestId())) {
//				return Message.error("请选择任务", Message.ERROR_CODE_PARAM);
//			}
//			String uuid = tasksTodoService.handleTaskTodoPost(dtoPost);
//			if (uuid == null) {
//				return Message.error("未查询到任务数据", Message.ERROR_CODE_EMPTY_DATA);
//			}
//			return Message.success("提交成功", uuid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Message.error("持久层异常", Message.ERROR_CODE_SQL);
//		}
//	}

	@GetMapping(value = "/citizenTask/{cdpfId}")
	@ApiOperation(value = "获取残疾人任务信息", notes = "获取残疾人任务信息")
	// @RequiresPermissions(value={"tasks:citizenTask"})
	@ApiImplicitParam(name = "cdpfId", value = "残疾人主键", required = true, paramType = "path")
	public Message get(@PathVariable @NotBlank(message = "残疾人不能为空") String cdpfId) {
		VoTask voTask = tasksAllService.findByCdpfId(cdpfId);
		return Message.success("成功", voTask);
	}

	@GetMapping(value = "/task/{requestId}")
	@ApiOperation(value = "获取指定任务信息", notes = "获取指定任务信息")
	// @RequiresPermissions(value={"tasks:task"})
	@ApiImplicitParam(name = "requestId", value = "任务主键", required = true, paramType = "path")
	public Message getTask(@PathVariable @NotBlank(message = "残疾人不能为空") String requestId) {
		VoRequest voRequest = requestService.findByUuid(requestId);
		return Message.success("成功", voRequest);
	}

	@GetMapping(value = "/taskdone/{requestId}")
	@ApiOperation(value = "获取指定已完结任务信息", notes = "获取指定已完结任务信息")
	// @RequiresPermissions(value={"tasks:taskdone"})
	@ApiImplicitParam(name = "requestId", value = "任务主键", required = true, paramType = "path")
	public Message getTaskDone(@PathVariable @NotBlank(message = "残疾人不能为空") String requestId) {
		VoRequest voRequest = historyService.findByRequestId(requestId);
		return Message.success("成功", voRequest);
	}

	@PostMapping("/tasksDid")
	@ApiOperation(value = "已办任务", notes = "已办任务")
	// @RequiresPermissions(value={"tasks:tasksDid"})
	@RequiresPermissions(value = { "yiban" })
	public Message tasksDid(@RequestBody PageBean pageBean) {
		Page<TasksDid> page = tasksDidService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/tasksDone")
	@ApiOperation(value = "已办结任务", notes = "已办结任务")
	// @RequiresPermissions(value={"tasks:tasksDone"})
	@RequiresPermissions(value = { "yibanjie" })
	public Message tasksDone(@RequestBody PageBean pageBean) {
		Page<History> page = tasksDoneService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/tasksAll")
	@ApiOperation(value = "全部任务", notes = "全部任务")
	// @RequiresPermissions(value={"tasks:tasksAll"})
	@RequiresPermissions(value = { "quanbu" })
	public Message tasksAll(@RequestBody PageBean pageBean) {
		Page<TasksAll> page = tasksAllService.findPage(pageBean);
		return Message.success("成功", page);
	}

	@PostMapping("/tasksLog")
	@ApiOperation(value = "任务历史", notes = "任务历史")
	// @RequiresPermissions(value={"tasks:tasksLog"})
	public Message tasksLog(@RequestBody PageBean pageBean) {
		pageBean.setOrderDirection(Order.Direction.desc);
		Page<RequestNote> page = requestNoteService.findPage(pageBean);
		return Message.success("成功", page);
	}

}
