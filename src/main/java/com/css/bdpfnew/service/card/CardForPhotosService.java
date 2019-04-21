/**
 * 
 */
package com.css.bdpfnew.service.card;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForPhotos;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年9月26日 下午4:12:30
 * @Description
 */

public interface CardForPhotosService extends BaseService<CardForPhotos, Long> {
	Integer getCountOfCardForPhotos(DtoRequest dtoRequest);
}
