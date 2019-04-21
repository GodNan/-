package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoIq;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfIq;
import com.css.bdpfnew.model.vo.VoIq;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.idt.IqRepository;
import com.css.bdpfnew.service.idt.IqService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:33:41
 * @Description:
 */
@Service
public class IqServiceImpl extends BaseServiceImpl<CdpfIq, Long> implements IqService {
	@Autowired
	private IqRepository iqRepository;
	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(IqRepository iqRepository) {
		super.setBaseDao(iqRepository);
	}

	@Override
	public VoIq findByUuid(String uuid) {
		CdpfIq iq = iqRepository.findByUuid(uuid);
		if (iq == null) {
			return null;
		}
		VoIq voIq = new VoIq();
		BeanUtils.copyProperties(iq, voIq);

		return voIq;
	}

	@Override
	public VoIq findByRequestId(String requestId) {
		CdpfIq iq = iqRepository.findByRequestId(requestId);
		if (iq == null) {
			return null;
		}
		VoIq voIq = new VoIq();
		BeanUtils.copyProperties(iq, voIq);

		return voIq;
	}

	@Override
	@Transactional
	public String save(DtoIq dtoIq) {
		CdpfIq iq = new CdpfIq();
		BeanUtils.copyProperties(dtoIq, iq);
		iq = iqRepository.save(iq);

		System.out.println("save - iq.getUuid():" + iq.getUuid());
		return iq.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoIq dtoIq) {
		CdpfIq iq = iqRepository.findByUuid(dtoIq.getUuid());
		BeanUtils.copyProperties(dtoIq, iq, new String[] { "uuid" });
		iq = iqRepository.save(iq);

		System.out.println("update - iq.getUuid():" + iq.getUuid());
		return iq.getUuid();
	}

	@Override
	public VoIq findOldByCdpfId(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
		if (citizen == null || citizen.getRequestIdFinished() == null) {
			return null;
		}

		CdpfIq iq = iqRepository.findByRequestId(citizen.getRequestIdFinished());
		if (iq == null) {
			return null;
		}
		VoIq voIq = new VoIq();
		BeanUtils.copyProperties(iq, voIq);

		return voIq;
	}

}