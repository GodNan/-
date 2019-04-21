package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfEar;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:10:30
 * @Description:
 */
public interface EarRepository extends BaseRepository<CdpfEar, Long> {
	CdpfEar findByUuid(String uuid);

	CdpfEar findByRequestId(String requestId);
}
