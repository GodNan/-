package com.css.bdpfnew.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.view.CardForApplys;
import com.css.bdpfnew.repository.CardForApplyRepository;
import com.css.bdpfnew.service.CardForApplyService;

/**
 * @Author HEYCH
 * @Date 2018年11月14日 上午10:33:17
 * @Description:
 */

@Service
public class CardForApplyServiceImpl extends BaseServiceImpl<CardForApplys, Long> implements CardForApplyService {

	@Autowired
	public void setBaseDao(CardForApplyRepository cardForApplyRepository) {
		super.setBaseDao(cardForApplyRepository);
	}

}
