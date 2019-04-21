package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfMental;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:16:07
 * @Description:
 */
public interface MentalRepository extends BaseRepository<CdpfMental, Long> {
	CdpfMental findByUuid(String uuid);

	CdpfMental findByRequestId(String requestId);
}