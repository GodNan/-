/**
 * 
 */
package com.css.bdpfnew.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.GonganData;
import com.css.bdpfnew.repository.GonganDataRepository;
import com.css.bdpfnew.service.GonganDataService;

/**
 * @Author erDuo
 * @Date 2018年12月23日 上午10:12:55
 * @Description
 */

@Service
public class GonganDataServiceImpl extends BaseServiceImpl<GonganData, Long> implements GonganDataService {

	@Autowired
	private GonganDataRepository gonganDataRepository;

	@Autowired
	public void setBaseDao(GonganDataRepository gonganDataRepository) {
		super.setBaseDao(gonganDataRepository);
	}

	@Override
	public GonganData findByCitizenId(String citizenId) {
		return gonganDataRepository.findByCitizenId(citizenId);
	}

}
