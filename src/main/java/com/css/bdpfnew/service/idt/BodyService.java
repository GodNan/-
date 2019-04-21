package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoBody;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfBody;
import com.css.bdpfnew.model.vo.VoBody;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:20:24
 * @Description:
 */
public interface BodyService extends BaseService<CdpfBody, Long> {
	VoBody findByUuid(String uuid);

	VoBody findByRequestId(String requestId);

	VoBody findOldByCdpfId(String cdpfId);

	@Transactional
	String save(DtoBody dtoBody);

	@Transactional
	String update(DtoBody dtoBody);
}
