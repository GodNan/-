/**
 * 
 */
package com.css.bdpfnew.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;
import com.css.bdpfnew.repository.CitizenNetRepository;
import com.css.bdpfnew.service.CitizenNetService;

/**
 * @Author erDuo
 * @Date 2018年10月22日 下午4:37:51
 * @Description
 */
@Service
public class CitizenNetServiceImpl extends BaseServiceImpl<CdpfCitizenNet, Long> implements CitizenNetService {

	@Autowired
	private CitizenNetRepository citizenNetRepository;

	@Autowired
	public void setBaseDao(CitizenNetRepository citizenNetRepository) {
		super.setBaseDao(citizenNetRepository);
	}

	@Override
	public CdpfCitizenNet findByUuid(String uuid) {
		return citizenNetRepository.findByUuid(uuid);
	}

}
