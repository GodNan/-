/**
 * 
 */
package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;

/**
 * @Author erDuo
 * @Date 2018年10月22日 下午4:28:55
 * @Description
 */

public interface CitizenNetRepository extends BaseRepository<CdpfCitizenNet, Long> {
	CdpfCitizenNet findByUuid(String uuid);
	CdpfCitizenNet findByRequestId(String requestId);
	CdpfCitizenNet findByCitizenId(String citizenId);
}