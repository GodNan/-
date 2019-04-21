/**
 * 
 */
package com.css.bdpfnew.service.impl.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForChanges;
import com.css.bdpfnew.repository.card.CardForChangesRepository;
import com.css.bdpfnew.service.card.CardForChangesService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年9月28日 上午10:25:33
 * @Description
 */

@Service
public class CardForChangesServiceImpl extends BaseServiceImpl<CardForChanges, Long> implements CardForChangesService {
	@Autowired
	private CardForChangesRepository cardForChangesRepository;

	@Autowired
	public void setBaseDao(CardForChangesRepository cardForChangesRepository) {
		super.setBaseDao(cardForChangesRepository);
	}

	@Override
	public Integer getCountOfCardForChanges(DtoRequest dtoRequest) {
		return cardForChangesRepository.countByCityidLike(dtoRequest.getCityid());
	}

}
