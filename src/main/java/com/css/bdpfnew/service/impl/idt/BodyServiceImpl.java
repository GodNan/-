package com.css.bdpfnew.service.impl.idt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoBody;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfBody;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.vo.VoBody;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.idt.BodyRepository;
import com.css.bdpfnew.service.idt.BodyService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:32:53
 * @Description:
 */
@Service
public class BodyServiceImpl extends BaseServiceImpl<CdpfBody, Long> implements BodyService {
	@Autowired
	private BodyRepository bodyRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(BodyRepository bodyRepository) {
		super.setBaseDao(bodyRepository);
	}

	@Override
	public VoBody findByUuid(String uuid) {
		CdpfBody body = bodyRepository.findByUuid(uuid);
		if (body == null) {
			return null;
		}
		VoBody voBody = new VoBody();
		BeanUtils.copyProperties(body, voBody);

		return voBody;
	}

	@Override
	public VoBody findByRequestId(String requestId) {
		CdpfBody body = bodyRepository.findByRequestId(requestId);
		if (body == null) {
			return null;
		}
		VoBody voBody = new VoBody();
		BeanUtils.copyProperties(body, voBody);

		return voBody;
	}

	@Override
	@Transactional
	public String save(DtoBody dtoBody) {
		CdpfBody body = new CdpfBody();
		BeanUtils.copyProperties(dtoBody, body);
		body = bodyRepository.save(body);

		return body.getUuid();

	}

	@Override
	@Transactional
	public String update(DtoBody dtoBody) {
		CdpfBody body = bodyRepository.findByUuid(dtoBody.getUuid());
		BeanUtils.copyProperties(dtoBody, body, new String[] { "uuid" });
		body = bodyRepository.save(body);

		System.out.println("update - body.getUuid():" + body.getUuid());
		return body.getUuid();
	}

	@Override
	public VoBody findOldByCdpfId(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
		if (citizen == null || citizen.getRequestIdFinished() == null) {
			return null;
		}

		CdpfBody body = bodyRepository.findByRequestId(citizen.getRequestIdFinished());
		if (body == null) {
			return null;
		}
		VoBody voBody = new VoBody();
		BeanUtils.copyProperties(body, voBody);

		return voBody;
	}

}