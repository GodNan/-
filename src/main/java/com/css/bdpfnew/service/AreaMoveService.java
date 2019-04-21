package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoAreaMove;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;

/**
 * @Author erDuo
 * @Date 2018年8月7日 上午9:42:27
 * @Description:
 */
public interface AreaMoveService extends BaseService<CdpfCitizen, Long> {

	@Transactional
	String areaMoveApply(DtoAreaMove dtoAreaMove);

	@Transactional
	String areaMoveAccept(DtoAreaMove dtoAreaMove);

}
