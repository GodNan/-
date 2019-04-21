/**
 * 
 */
package com.css.bdpfnew.service.card;

import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.entity.bdpfnew.view.CardForChanges;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年9月28日 上午10:24:24
 * @Description
 */

public interface CardForChangesService extends BaseService<CardForChanges, Long> {
	Integer getCountOfCardForChanges(DtoRequest dtoRequest);
}
