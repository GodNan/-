package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenKsqr;

/**
 * @Author HEYCH
 * @Date 2018年11月21日 上午14:30:18
 * @Description:
 */

public interface KsqrJiekouService extends BaseService<CdpfCitizen, Long> {
	
	String getCitizenInfo(String citizenId, String citizenName);
	
	@Transactional
	String saveCitizen(CdpfCitizenKsqr info);
	
}
