/**
 * 
 */
package com.css.bdpfnew.repository.card;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCardLog;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午4:15:31
 * @Description
 */

public interface CdpfCardLogRepository extends BaseRepository<CdpfCardLog, Long> {
	CdpfCardLog findByUuid(String uuid);

	CdpfCardLog findByRequestId(String requestId);
}
