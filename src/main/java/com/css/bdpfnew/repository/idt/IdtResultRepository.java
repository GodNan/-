package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.IdtResult;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年7月26日 下午4:05:52
 * @Description:
 */
public interface IdtResultRepository extends BaseRepository<IdtResult, Long> {
	IdtResult findByUuid(String uuid);

	IdtResult findByRequestId(String requestId);
}