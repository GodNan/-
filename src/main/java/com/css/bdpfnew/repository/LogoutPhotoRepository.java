/**
 * 
 */
package com.css.bdpfnew.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenLogoutPhotos;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:09:08
 * @Description
 */
public interface LogoutPhotoRepository extends BaseRepository<CitizenLogoutPhotos, Long> {
	CitizenLogoutPhotos findByUuid(String uuid);

	CitizenLogoutPhotos findByCdpfId(String cdpfId);

	CitizenLogoutPhotos findByCitizenId(String citizenId);

	@Modifying
	@Query("update CitizenLogoutPhotos set delFlag = 1 where uuid = :uuid")
	void deleteByUuid(@Param("uuid") String uuid);
	
	@Transactional
	@Modifying
	@Query("update CitizenLogoutPhotos set citizenId = :newCitizenId where cdpfId = :cdpfId")
	void updateByCdpfId(@Param("cdpfId") String cdpfId, @Param("newCitizenId") String newCitizenId);
	
}
