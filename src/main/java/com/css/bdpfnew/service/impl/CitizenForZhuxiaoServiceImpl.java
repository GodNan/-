package com.css.bdpfnew.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenForZhuxiaos;
import com.css.bdpfnew.repository.CitizenForZhuxiaoRepository;
import com.css.bdpfnew.service.CitizenForZhuxiaoService;

/**
 * @Author HEYCH
 * @Date 2018年11月14日 上午10:33:17
 * @Description:
 */

@Service
public class CitizenForZhuxiaoServiceImpl extends BaseServiceImpl<CitizenForZhuxiaos, Long> implements CitizenForZhuxiaoService {

	@Autowired
	public void setBaseDao(CitizenForZhuxiaoRepository citizenForZhuxiaoRepository) {
		super.setBaseDao(citizenForZhuxiaoRepository);
	}

}
