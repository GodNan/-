package com.css.bdpfnew.service.impl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.repository.task.TasksDoneRepository;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.TasksDoneService;

/**
 * @Author erDuo
 * @Date 2018年6月20日 上午10:00:18
 * @Description:
 */

@Service
public class TasksDoneServiceImpl extends BaseServiceImpl<History, Long> implements TasksDoneService {
	@Autowired
	private TasksDoneRepository tasksDoneRepository;

	@Autowired
	public void setBaseDao(TasksDoneRepository tasksDoneRepository) {
		super.setBaseDao(tasksDoneRepository);
	}

}
