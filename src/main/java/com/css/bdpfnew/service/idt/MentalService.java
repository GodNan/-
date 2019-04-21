package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoMental;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfMental;
import com.css.bdpfnew.model.vo.VoMental;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:21:25
 * @Description:
 */
public interface MentalService extends BaseService<CdpfMental, Long> {
	VoMental findByUuid(String uuid);

	VoMental findByRequestId(String requestId);
	
	VoMental findOldByCdpfId(String cdpfId);

	@Transactional
	String save(DtoMental dtoMental);

	@Transactional
	String update(DtoMental dtoMental);
}
