/**
 * 
 */
package com.css.bdpfnew.service.impl.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.vo.VoRequest;
import com.css.bdpfnew.repository.HistoryRepository;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.HistoryService;

/**
 * @Author erDuo
 * @Date 2018年10月10日 下午4:47:26
 * @Description
 */

@Service
public class HistoryServiceImpl extends BaseServiceImpl<History, Long> implements HistoryService {
	@Autowired
	private HistoryRepository HistoryRepository;

	@Autowired
	public void setBaseDao(HistoryRepository HistoryRepository) {
		super.setBaseDao(HistoryRepository);
	}

	@Override
	public VoRequest findByUuid(String uuid) {

		History History = HistoryRepository.findByUuid(uuid);
		if (History == null) {
			return null;
		}
		VoRequest voRequest = new VoRequest();
		BeanUtils.copyProperties(History, voRequest);

		return voRequest;
	}

	@Override
	public VoRequest findByRequestId(String requestId) {
		History History = HistoryRepository.findByRequestId(requestId);
		if (History == null) {
			return null;
		}
		VoRequest voRequest = new VoRequest();
		BeanUtils.copyProperties(History, voRequest);

		return voRequest;
	}

}