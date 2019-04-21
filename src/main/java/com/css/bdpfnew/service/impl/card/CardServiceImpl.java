/**
 * 
 */
package com.css.bdpfnew.service.impl.card;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoCdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.vo.VoCdpfCard;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.card.CardService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午2:42:07
 * @Description
 */

@Service
public class CardServiceImpl extends BaseServiceImpl<CdpfCard, Long> implements CardService {
	@Autowired
	private CdpfCardRepository cdpfCardRepository;

	@Autowired
	public void setBaseDao(CdpfCardRepository cdpfCardRepository) {
		super.setBaseDao(cdpfCardRepository);
	}

	@Override
	public VoCdpfCard findByUuid(String uuid) {

		CdpfCard card = cdpfCardRepository.findByUuid(uuid);

		if (card == null) {
			return null;
		}
		VoCdpfCard voCard = new VoCdpfCard();
		BeanUtils.copyProperties(card, voCard);
		return voCard;
	}
	
	@Override
	public CdpfCard findByRequestId(String requestId) {
		CdpfCard card = cdpfCardRepository.findByRequestId(requestId);
		return card;
	};

	@Override
	public String save(DtoCdpfCard dtoCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(DtoCdpfCard dtoCard) {
		// TODO Auto-generated method stub
		return null;
	}

}
