/**
 * 
 */
package com.css.bdpfnew.service.impl.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForGives;
import com.css.bdpfnew.repository.card.CardForGivesRepository;
import com.css.bdpfnew.service.card.CardForGivesService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:38:18
 * @Description
 */

@Service
public class CardForGivesServiceImpl extends BaseServiceImpl<CardForGives, Long> implements CardForGivesService {
	@Autowired
	private CardForGivesRepository cardForGivesRepository;

	@Autowired
	public void setBaseDao(CardForGivesRepository cardForGivesRepository) {
		super.setBaseDao(cardForGivesRepository);
	}

	@Override
	public Integer getCountOfCardForGives(DtoRequest dtoRequest) {
		return cardForGivesRepository.countByCityidLike(dtoRequest.getCityid());
	}

}