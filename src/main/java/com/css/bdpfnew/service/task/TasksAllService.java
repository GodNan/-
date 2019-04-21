package com.css.bdpfnew.service.task;

import com.css.bdpfnew.model.entity.bdpfnew.view.TasksAll;
import com.css.bdpfnew.model.vo.VoTask;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月20日 上午10:45:30
 * @Description:
 */

public interface TasksAllService extends BaseService<TasksAll, Long> {
	VoTask findByCdpfId(String cdpfId);
}
