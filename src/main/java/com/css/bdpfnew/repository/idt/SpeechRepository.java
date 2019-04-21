package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfSpeech;
import com.css.bdpfnew.repository.BaseRepository;

/**
* @Author erDuo 
* @Date 2018年6月29日 下午4:13:03
* @Description:
*/
public interface SpeechRepository extends BaseRepository<CdpfSpeech, Long> {
	CdpfSpeech findByUuid(String uuid);

	CdpfSpeech findByRequestId(String requestId);
}

