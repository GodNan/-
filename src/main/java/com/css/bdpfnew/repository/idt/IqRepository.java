package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfIq;
import com.css.bdpfnew.repository.BaseRepository;

/**
* @Author erDuo 
* @Date 2018年6月29日 下午4:15:04
* @Description:
*/
public interface IqRepository extends BaseRepository<CdpfIq, Long> {
	CdpfIq findByUuid(String uuid);

	CdpfIq findByRequestId(String requestId);
}
