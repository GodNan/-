package com.css.bdpfnew.service.impl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.view.TasksDid;
import com.css.bdpfnew.repository.task.TasksDidRepository;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.TasksDidService;

/**
 * @Author erDuo
 * @Date 2018年6月19日 下午4:50:16
 * @Description:
 */

@Service
public class TasksDidServiceImpl extends BaseServiceImpl<TasksDid, Long> implements TasksDidService {
	@Autowired
	private TasksDidRepository tasksDidRepository;

	@Autowired
	public void setBaseDao(TasksDidRepository tasksDidRepository) {
		super.setBaseDao(tasksDidRepository);
	}

}