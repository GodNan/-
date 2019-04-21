package com.css.bdpfnew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfMiddleArea;

/**
 * @Author HEYCH
 * @Date 2018年12月16日 上午10:31:56
 * @Description:
 */

public interface CdpfMiddleAreaRepository extends BaseRepository<CdpfMiddleArea, Long> {
	
	@Query("select m from CdpfMiddleArea m where status = '0' and personalId = :personalId ")
	List<CdpfMiddleArea> findByPersonalId(@Param("personalId") String personalId);
	
	@Query("select m from CdpfMiddleArea m where status = '0' and cdpfId = :cdpfId ")
	CdpfMiddleArea findByCdpfId(@Param("cdpfId") String cdpfId);
}
