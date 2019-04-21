package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfBody;
import com.css.bdpfnew.repository.BaseRepository;

/**
* @Author erDuo 
* @Date 2018年6月29日 下午4:14:15
* @Description:
*/
public interface BodyRepository extends BaseRepository<CdpfBody, Long> {
	CdpfBody findByUuid(String uuid);

	CdpfBody findByRequestId(String requestId);
}
