/**
 * 
 */
package com.css.bdpfnew.service.card;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoCdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.vo.VoCdpfCard;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午2:40:38
 * @Description
 */

public interface CardService extends BaseService<CdpfCard, Long> {
	VoCdpfCard findByUuid(String uuid);
	
	CdpfCard findByRequestId(String requestId);

	@Transactional
	String save(DtoCdpfCard dtoCard);

	@Transactional
	String update(DtoCdpfCard dtoCard);
}
