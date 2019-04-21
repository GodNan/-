package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoSpeech;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfSpeech;
import com.css.bdpfnew.model.vo.VoSpeech;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:19:51
 * @Description:
 */
public interface SpeechService extends BaseService<CdpfSpeech, Long> {
	VoSpeech findByUuid(String uuid);

	VoSpeech findByRequestId(String requestId);
	
	VoSpeech findOldByCdpfId(String cdpfId);

	@Transactional
	String save(DtoSpeech dtoSpeech);

	@Transactional
	String update(DtoSpeech dtoSpeech);
}
