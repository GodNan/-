package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoBatchAreaAccept;
import com.css.bdpfnew.model.dto.DtoBatchAreaMove;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;

/**
 * @Author HEYCH
 * @Date 2018年12月12日 上午9:42:27
 * @Description:
 */
public interface BatchAreaMoveService extends BaseService<CdpfCitizen, Long> {

	@Transactional
	void areaBatchMoveApply(DtoBatchAreaMove dtoBatchAreaMove);

	@Transactional
	void areaBatchMoveAccept(DtoBatchAreaAccept dtoBatchAreaAccept);
	
	@Transactional
	void areaBatchMoveNotAccept(DtoBatchAreaAccept dtoBatchAreaAccept);

}
