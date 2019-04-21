package com.css.bdpfnew.repository;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.History;

/**
 * @Author erDuo
 * @Date 2018年6月14日 下午3:13:04
 * @Description:
 */
public interface HistoryRepository extends BaseRepository<History, Long> {

	History findByUuid(String uuid);

	History findByRequestId(String requestId);

	History findByCdpfId(String cdpfId);
	
	List<History> findByCitizenId(String citizenId);

}
