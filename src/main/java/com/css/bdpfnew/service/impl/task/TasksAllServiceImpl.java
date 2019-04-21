package com.css.bdpfnew.service.impl.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.entity.bdpfnew.view.TasksAll;
import com.css.bdpfnew.model.vo.VoTask;
import com.css.bdpfnew.repository.HistoryRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.repository.task.TasksAllRepository;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.TasksAllService;

/**
 * @Author erDuo
 * @Date 2018年6月20日 上午10:46:37
 * @Description:
 */
@Service
public class TasksAllServiceImpl extends BaseServiceImpl<TasksAll, Long> implements TasksAllService {
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	public void setBaseDao(TasksAllRepository tasksAllRepository) {
		super.setBaseDao(tasksAllRepository);
	}

	@Override
	public VoTask findByCdpfId(String cdpfId) {
		Request request = requestRepository.findByCdpfId(cdpfId);
		VoTask voTask = new VoTask();
		if (request != null) {
			BeanUtils.copyProperties(request, voTask);
			return voTask;
		} else {
			History history = historyRepository.findByCdpfId(cdpfId);
			if (history != null) {
				BeanUtils.copyProperties(history, voTask);
				return voTask;
			}
		}

		return null;
	}

}
