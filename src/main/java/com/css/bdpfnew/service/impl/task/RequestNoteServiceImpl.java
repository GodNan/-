package com.css.bdpfnew.service.impl.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoRequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.vo.VoRequestNote;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.RequestNoteService;

/**
 * @Author erDuo
 * @Date 2018年7月6日 上午11:32:54
 * @Description:
 */
@Service
public class RequestNoteServiceImpl extends BaseServiceImpl<RequestNote, Long> implements RequestNoteService {
	@Autowired
	private RequestNoteRepository requestNoteRepository;

	@Autowired
	public void setBaseDao(RequestNoteRepository requestNoteRepository) {
		super.setBaseDao(requestNoteRepository);
	}

	@Override
	public String save(DtoRequestNote dtoRequestNote) {
		RequestNote requestNote = new RequestNote();
		BeanUtils.copyProperties(dtoRequestNote, requestNote);
		requestNote = requestNoteRepository.save(requestNote);

		return requestNote.getUuid();
	}

	@Override
	public String update(DtoRequestNote dtoRequestNote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoRequestNote findByUuid(String uuid) {
		RequestNote requestNote = requestNoteRepository.findByUuid(uuid);
		if (requestNote == null) {
			return null;
		}
		VoRequestNote voRequestNote = new VoRequestNote();
		BeanUtils.copyProperties(requestNote, voRequestNote);

		return voRequestNote;
	}

}
