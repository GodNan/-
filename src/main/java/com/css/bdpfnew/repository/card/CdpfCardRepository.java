/**
 * 
 */
package com.css.bdpfnew.repository.card;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年9月19日 下午4:19:16
 * @Description
 */

public interface CdpfCardRepository extends BaseRepository<CdpfCard, Long> {
	CdpfCard findByUuid(String uuid);

	CdpfCard findByRequestId(String requestId);
	
	@Query("select c from CdpfCard c where citizenId = :citizenId order by modifyDate desc ")
	List<CdpfCard> findByCitizenId(@Param("citizenId") String citizenId);
	
}
