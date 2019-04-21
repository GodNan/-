package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfEye;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:08:07
 * @Description:
 */
public interface EyeRepository extends BaseRepository<CdpfEye, Long> {
	CdpfEye findByUuid(String uuid);

	CdpfEye findByRequestId(String requestId);
}
