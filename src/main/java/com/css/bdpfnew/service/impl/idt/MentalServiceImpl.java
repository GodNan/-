package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoMental;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfMental;
import com.css.bdpfnew.model.vo.VoMental;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.idt.MentalRepository;
import com.css.bdpfnew.service.idt.MentalService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:34:23
 * @Description:
 */
@Service
public class MentalServiceImpl extends BaseServiceImpl<CdpfMental, Long> implements MentalService {
	@Autowired
	private MentalRepository mentalRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(MentalRepository mentalRepository) {
		super.setBaseDao(mentalRepository);
	}

	@Override
	public VoMental findByUuid(String uuid) {
		CdpfMental mental = mentalRepository.findByUuid(uuid);
		if (mental == null) {
			return null;
		}
		VoMental voMental = new VoMental();
		BeanUtils.copyProperties(mental, voMental);

		return voMental;
	}

	@Override
	public VoMental findByRequestId(String requestId) {
		CdpfMental mental = mentalRepository.findByRequestId(requestId);
		if (mental == null) {
			return null;
		}
		VoMental voMental = new VoMental();
		BeanUtils.copyProperties(mental, voMental);

		return voMental;
	}

	@Override
	@Transactional
	public String save(DtoMental dtoMental) {
		CdpfMental mental = new CdpfMental();
		BeanUtils.copyProperties(dtoMental, mental);
		mental = mentalRepository.save(mental);

		System.out.println("save - mental.getUuid():" + mental.getUuid());
		return mental.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoMental dtoMental) {
		CdpfMental mental = mentalRepository.findByUuid(dtoMental.getUuid());
		BeanUtils.copyProperties(dtoMental, mental, new String[] { "uuid" });
		mental = mentalRepository.save(mental);

		System.out.println("update - mental.getUuid():" + mental.getUuid());
		return mental.getUuid();
	}

	@Override
	public VoMental findOldByCdpfId(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
		if (citizen == null || citizen.getRequestIdFinished() == null) {
			return null;
		}

		CdpfMental mental = mentalRepository.findByRequestId(citizen.getRequestIdFinished());
		if (mental == null) {
			return null;
		}
		VoMental voMental = new VoMental();
		BeanUtils.copyProperties(mental, voMental);

		return voMental;
	}

}