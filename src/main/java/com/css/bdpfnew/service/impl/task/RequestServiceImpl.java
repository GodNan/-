package com.css.bdpfnew.service.impl.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.vo.VoRequest;
import com.css.bdpfnew.repository.HistoryRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.RequestService;

/**
 * @Author erDuo
 * @Date 2018年6月14日 下午3:09:35
 * @Description:
 */
@Service
public class RequestServiceImpl extends BaseServiceImpl<Request, Long> implements RequestService {
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	public void setBaseDao(RequestRepository requestRepository) {
		super.setBaseDao(requestRepository);
	}

	@Override
	public VoRequest findByUuid(String uuid) {

		Request request = requestRepository.findByUuid(uuid);
		VoRequest voRequest = new VoRequest();
		if (request == null) {
			History history = historyRepository.findByRequestId(uuid);
			if (history == null) {
				return null;
			} else {
				BeanUtils.copyProperties(history, voRequest);
			}
		} else {
			BeanUtils.copyProperties(request, voRequest);
		}

		return voRequest;
	}

}
