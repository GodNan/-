package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoEye;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEye;
import com.css.bdpfnew.model.vo.VoEye;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.idt.EyeRepository;
import com.css.bdpfnew.service.idt.EyeService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:22:26
 * @Description:
 */
@Service
public class EyeServiceImpl extends BaseServiceImpl<CdpfEye, Long> implements EyeService {
	@Autowired
	private EyeRepository eyeRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(EyeRepository eyeRepository) {
		super.setBaseDao(eyeRepository);
	}

	@Override
	public VoEye findByUuid(String uuid) {
		CdpfEye eye = eyeRepository.findByUuid(uuid);
		if (eye == null) {
			return null;
		}
		VoEye voEye = new VoEye();
		BeanUtils.copyProperties(eye, voEye);

		return voEye;
	}

	@Override
	public VoEye findByRequestId(String requestId) {
		CdpfEye eye = eyeRepository.findByRequestId(requestId);
		if (eye == null) {
			return null;
		}

		VoEye voEye = new VoEye();
		BeanUtils.copyProperties(eye, voEye);

		return voEye;
	}

	@Override
	@Transactional
	public String save(DtoEye dtoEye) {
		CdpfEye eye = new CdpfEye();
		BeanUtils.copyProperties(dtoEye, eye);
		eye = eyeRepository.save(eye);

		System.out.println("save - eye.getUuid():" + eye.getUuid());
		return eye.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoEye dtoEye) {
		CdpfEye eye = eyeRepository.findByUuid(dtoEye.getUuid());
		BeanUtils.copyProperties(dtoEye, eye, new String[] { "uuid" });
		eye = eyeRepository.save(eye);

		System.out.println("update - eye.getUuid():" + eye.getUuid());
		return eye.getUuid();
	}

	@Override
	public VoEye findOldByCdpfId(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
		if (citizen == null || citizen.getRequestIdFinished() == null) {
			return null;
		}

		CdpfEye eye = eyeRepository.findByRequestId(citizen.getRequestIdFinished());
		if (eye == null) {
			return null;
		}

		VoEye voEye = new VoEye();
		BeanUtils.copyProperties(eye, voEye);

		return voEye;
	}

}
