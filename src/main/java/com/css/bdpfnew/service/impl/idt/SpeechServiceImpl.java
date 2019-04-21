package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoSpeech;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfSpeech;
import com.css.bdpfnew.model.vo.VoSpeech;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.idt.SpeechRepository;
import com.css.bdpfnew.service.idt.SpeechService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:31:41
 * @Description:
 */
@Service
public class SpeechServiceImpl extends BaseServiceImpl<CdpfSpeech, Long> implements SpeechService {
	@Autowired
	private SpeechRepository speechRepository;
	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(SpeechRepository speechRepository) {
		super.setBaseDao(speechRepository);
	}

	@Override
	public VoSpeech findByUuid(String uuid) {
		CdpfSpeech speech = speechRepository.findByUuid(uuid);
		if (speech == null) {
			return null;
		}
		VoSpeech voSpeech = new VoSpeech();
		BeanUtils.copyProperties(speech, voSpeech);

		return voSpeech;
	}

	@Override
	public VoSpeech findByRequestId(String requestId) {
		CdpfSpeech speech = speechRepository.findByRequestId(requestId);
		if (speech == null) {
			return null;
		}
		VoSpeech voSpeech = new VoSpeech();
		BeanUtils.copyProperties(speech, voSpeech);

		return voSpeech;
	}

	@Override
	@Transactional
	public String save(DtoSpeech dtoSpeech) {
		CdpfSpeech speech = new CdpfSpeech();
		BeanUtils.copyProperties(dtoSpeech, speech);
		speech = speechRepository.save(speech);

		System.out.println("save - speech.getUuid():" + speech.getUuid());
		return speech.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoSpeech dtoSpeech) {
		CdpfSpeech speech = speechRepository.findByUuid(dtoSpeech.getUuid());
		BeanUtils.copyProperties(dtoSpeech, speech, new String[] { "uuid" });
		speech = speechRepository.save(speech);

		System.out.println("update - speech.getUuid():" + speech.getUuid());
		return speech.getUuid();
	}

	@Override
	public VoSpeech findOldByCdpfId(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
		if (citizen == null || citizen.getRequestIdFinished() == null) {
			return null;
		}

		CdpfSpeech speech = speechRepository.findByRequestId(citizen.getRequestIdFinished());
		if (speech == null) {
			return null;
		}
		VoSpeech voSpeech = new VoSpeech();
		BeanUtils.copyProperties(speech, voSpeech);

		return voSpeech;
	}

}
