package com.css.bdpfnew.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenForAccepts;
import com.css.bdpfnew.repository.CitizenForAcceptsRepository;
import com.css.bdpfnew.service.CitizenForAcceptsService;

/**
 * @Author HEYCH
 * @Date 2018年11月14日 上午10:33:17
 * @Description:
 */

@Service
public class CitizenForAcceptsServiceImpl extends BaseServiceImpl<CitizenForAccepts, Long> implements CitizenForAcceptsService {

	@Autowired
	public void setBaseDao(CitizenForAcceptsRepository citizenForAcceptsRepository) {
		super.setBaseDao(citizenForAcceptsRepository);
	}

}
