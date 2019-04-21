/**
 * 
 */
package com.css.bdpfnew.service.impl.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForDelays;
import com.css.bdpfnew.repository.card.CardForDelaysRepository;
import com.css.bdpfnew.service.card.CardForDelaysService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:38:08
 * @Description
 */

@Service
public class CardForDelaysServiceImpl extends BaseServiceImpl<CardForDelays, Long> implements CardForDelaysService {
	@Autowired
	private CardForDelaysRepository cardForDelaysRepository;

	@Autowired
	public void setBaseDao(CardForDelaysRepository cardForDelaysRepository) {
		super.setBaseDao(cardForDelaysRepository);
	}

	@Override
	public Integer getCountOfCardForDelays(DtoRequest dtoRequest) {
		return cardForDelaysRepository.countByCityidLike(dtoRequest.getCityid());
	}

}
