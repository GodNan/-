package com.css.bdpfnew.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfMiddleArea;
import com.css.bdpfnew.repository.CdpfMiddleAreaRepository;
import com.css.bdpfnew.service.CdpfMiddleAreaService;

@Service
public class CdpfMiddleAreaServiceImpl extends BaseServiceImpl<CdpfMiddleArea, Long> implements CdpfMiddleAreaService {

	@Autowired
	private CdpfMiddleAreaRepository cdpfMiddleAreaRepository;

	@Autowired
	public void setBaseDao(CdpfMiddleAreaRepository cdpfMiddleAreaRepository) {
		super.setBaseDao(cdpfMiddleAreaRepository);
	}
	
	@Override
	public List<CdpfMiddleArea> findByPersonalId(String personalId) {
		return cdpfMiddleAreaRepository.findByPersonalId(personalId);
	};
	
	@Override
	public CdpfMiddleArea findByCdpfId(String cdpfId) {
		return cdpfMiddleAreaRepository.findByCdpfId(cdpfId);
	};
	
}
