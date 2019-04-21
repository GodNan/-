package com.css.bdpfnew.repository;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;

/**
 * @Author erDuo
 * @Date 2018年6月22日 上午10:31:56
 * @Description:
 */

public interface CitizenRepository extends BaseRepository<CdpfCitizen, Long> {
	CdpfCitizen findByUuid(String uuid);
	CdpfCitizen findByRequestId(String requestId);
	CdpfCitizen findByCitizenId(String citizenId);
	List<CdpfCitizen> findByCityidLike(String cityid);
}
