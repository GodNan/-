package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoIq;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfIq;
import com.css.bdpfnew.model.vo.VoIq;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:20:55
 * @Description:
 */
public interface IqService extends BaseService<CdpfIq, Long> {
	VoIq findByUuid(String uuid);

	VoIq findByRequestId(String requestId);

	VoIq findOldByCdpfId(String cdpfId);

	@Transactional
	String save(DtoIq dtoIq);

	@Transactional
	String update(DtoIq dtoIq);
}