/**
 * 
 */
package com.css.bdpfnew.service.impl.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForPhotos;
import com.css.bdpfnew.repository.card.CardForPhotosRepository;
import com.css.bdpfnew.service.card.CardForPhotosService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年9月26日 下午4:13:25
 * @Description
 */
@Service
public class CardForPhotosServiceImpl extends BaseServiceImpl<CardForPhotos, Long> implements CardForPhotosService {
	@Autowired
	private CardForPhotosRepository cardForPhotosRepository;

	@Autowired
	public void setBaseDao(CardForPhotosRepository cardForPhotosRepository) {
		super.setBaseDao(cardForPhotosRepository);
	}

	@Override
	public Integer getCountOfCardForPhotos(DtoRequest dtoRequest) {
		// TODO Auto-generated method stub
		return cardForPhotosRepository.countByCityidLike(dtoRequest.getCityid());
	}

}