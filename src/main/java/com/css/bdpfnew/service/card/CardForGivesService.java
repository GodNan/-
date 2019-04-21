/**
 * 
 */
package com.css.bdpfnew.service.card;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForGives;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:36:36
 * @Description
 */


public interface CardForGivesService extends BaseService<CardForGives, Long> {
	Integer getCountOfCardForGives(DtoRequest dtoRequest);
}