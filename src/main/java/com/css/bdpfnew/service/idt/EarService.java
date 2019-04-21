package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoEar;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEar;
import com.css.bdpfnew.model.vo.VoEar;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:18:45
 * @Description:
 */
public interface EarService extends BaseService<CdpfEar, Long> {
	VoEar findByUuid(String uuid);

	VoEar findByRequestId(String requestId);

	VoEar findOldByCdpfId(String cdpfId);

	@Transactional
	String save(DtoEar dtoEar);

	@Transactional
	String update(DtoEar dtoEar);
}
