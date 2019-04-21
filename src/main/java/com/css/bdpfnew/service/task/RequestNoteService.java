package com.css.bdpfnew.service.task;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoRequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.vo.VoRequestNote;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年7月6日 上午11:30:52
 * @Description:
 */

public interface RequestNoteService extends BaseService<RequestNote, Long> {

	VoRequestNote findByUuid(String uuid);

	@Transactional
	String save(DtoRequestNote dtoRequestNote);

	@Transactional
	String update(DtoRequestNote dtoRequestNote);
}
