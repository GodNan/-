package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoEar;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEar;
import com.css.bdpfnew.model.vo.VoEar;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.idt.EarRepository;
import com.css.bdpfnew.service.idt.EarService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:30:16
 * @Description:
 */
@Service
public class EarServiceImpl extends BaseServiceImpl<CdpfEar, Long> implements EarService {
	@Autowired
	private EarRepository earRepository;
	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(EarRepository earRepository) {
		super.setBaseDao(earRepository);
	}

	@Override
	public VoEar findByUuid(String uuid) {
		CdpfEar ear = earRepository.findByUuid(uuid);
		if (ear == null) {
			return null;
		}
		VoEar voEar = new VoEar();
		BeanUtils.copyProperties(ear, voEar);

		return voEar;
	}

	@Override
	public VoEar findByRequestId(String requestId) {
		CdpfEar ear = earRepository.findByRequestId(requestId);
		if (ear == null) {
			return null;
		}
		VoEar voEar = new VoEar();
		BeanUtils.copyProperties(ear, voEar);

		return voEar;
	}

	@Override
	@Transactional
	public String save(DtoEar dtoEar) {
		CdpfEar ear = new CdpfEar();
		BeanUtils.copyProperties(dtoEar, ear);
		ear = earRepository.save(ear);

		System.out.println("save - ear.getUuid():" + ear.getUuid());
		return ear.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoEar dtoEar) {
		CdpfEar ear = earRepository.findByUuid(dtoEar.getUuid());
		BeanUtils.copyProperties(dtoEar, ear, new String[] { "uuid" });
		ear = earRepository.save(ear);

		System.out.println("update - ear.getUuid():" + ear.getUuid());
		return ear.getUuid();
	}

	@Override
	public VoEar findOldByCdpfId(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
		if (citizen == null || citizen.getRequestIdFinished() == null) {
			return null;
		}

		CdpfEar ear = earRepository.findByRequestId(citizen.getRequestIdFinished());
		if (ear == null) {
			return null;
		}
		VoEar voEar = new VoEar();
		BeanUtils.copyProperties(ear, voEar);

		return voEar;
	}

}
