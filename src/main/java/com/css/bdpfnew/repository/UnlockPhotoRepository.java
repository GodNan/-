/**
 * 
 */
package com.css.bdpfnew.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenJiedongPhoto;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:09:08
 * @Description
 */
public interface UnlockPhotoRepository extends BaseRepository<CitizenJiedongPhoto, Long> {
	CitizenJiedongPhoto findByUuid(String uuid);

	CitizenJiedongPhoto findByCdpfId(String cdpfId);

	CitizenJiedongPhoto findByCitizenId(String citizenId);
	
	@Transactional
	@Modifying
	@Query("update CitizenJiedongPhoto set citizenId = :newCitizenId where cdpfId = :cdpfId")
	void updateByCdpfId(@Param("cdpfId") String cdpfId, @Param("newCitizenId") String newCitizenId);

}
