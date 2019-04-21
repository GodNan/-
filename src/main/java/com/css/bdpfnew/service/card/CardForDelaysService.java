/**
 * 
 */
package com.css.bdpfnew.service.card;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForDelays;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:36:25
 * @Description
 */

public interface CardForDelaysService extends BaseService<CardForDelays, Long> {
	Integer getCountOfCardForDelays(DtoRequest dtoRequest);
}
