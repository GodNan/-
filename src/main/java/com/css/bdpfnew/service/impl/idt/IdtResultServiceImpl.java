package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.IdtResult;
import com.css.bdpfnew.model.vo.VoIdtResult;
import com.css.bdpfnew.repository.idt.IdtResultRepository;
import com.css.bdpfnew.service.idt.IdtResultService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年8月1日 下午4:02:23
 * @Description:
 */

@Service
public class IdtResultServiceImpl extends BaseServiceImpl<IdtResult, Long> implements IdtResultService {

	@Autowired
	private IdtResultRepository idtResultRepository;

	@Autowired
	public void setBaseDao(IdtResultRepository idtResultRepository) {
		super.setBaseDao(idtResultRepository);
	}

	@Override
	public VoIdtResult findByUuid(String uuid) {
		IdtResult idtResult = idtResultRepository.findByUuid(uuid);
		if (idtResult == null) {
			return null;
		}
		VoIdtResult voIdtResult = new VoIdtResult();
		BeanUtils.copyProperties(idtResult, voIdtResult);

		return voIdtResult;
	}

	@Override
	public VoIdtResult findByRequestId(String requestId) {
		IdtResult idtResult = idtResultRepository.findByRequestId(requestId);
		if (idtResult == null) {
			return null;
		}

		VoIdtResult voIdtResult = new VoIdtResult();
		BeanUtils.copyProperties(idtResult, voIdtResult);

		return voIdtResult;
	}

}
