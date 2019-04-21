package com.css.bdpfnew.service.task;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoNewTask;
import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.dto.DtoTask;
import com.css.bdpfnew.model.entity.bdpfnew.view.TasksTodo;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月19日 下午3:07:04
 * @Description:
 */

public interface TasksTodoService extends BaseService<TasksTodo, Long> {
	Integer getCountOfTasksToDo(DtoRequest dtoRequest);

	@Transactional
	String handleTaskTodo(DtoTask dtoTask);

	// @Transactional
	// String handleTaskTodoPost(DtoPost dtoPost);

	@Transactional
	String newTask(DtoNewTask dtoNewTask);

}