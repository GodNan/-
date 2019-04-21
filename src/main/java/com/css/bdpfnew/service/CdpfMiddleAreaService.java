package com.css.bdpfnew.service;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfMiddleArea;

public interface CdpfMiddleAreaService extends BaseService<CdpfMiddleArea, Long> {

	List<CdpfMiddleArea> findByPersonalId(String personalId);
	
	CdpfMiddleArea findByCdpfId(String cdpfId);

}
