package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoEye;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEye;
import com.css.bdpfnew.model.vo.VoEye;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:17:44
 * @Description:
 */
public interface EyeService extends BaseService<CdpfEye, Long> {
	VoEye findByUuid(String uuid);

	VoEye findByRequestId(String requestId);

	VoEye findOldByCdpfId(String cdpfId);

	@Transactional
	String save(DtoEye dtoEye);

	@Transactional
	String update(DtoEye dtoEye);
}
