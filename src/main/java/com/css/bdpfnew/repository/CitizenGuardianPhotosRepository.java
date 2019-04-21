/**
 * 
 */
package com.css.bdpfnew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenGuardianPhotos;

/**
 * @Author erDuo
 * @Date 2018年12月17日 下午7:01:26
 * @Description
 */

public interface CitizenGuardianPhotosRepository extends BaseRepository<CitizenGuardianPhotos, Long> {

	CitizenGuardianPhotos findByUuid(String uuid);

	List<CitizenGuardianPhotos> findByCdpfid(String cdpfid);

	@Modifying
	@Query("update CitizenGuardianPhotos set delFlag = 1 where uuid = :uuid")
	void deleteByUuid(@Param("uuid") String uuid);
	
	Integer countByCdpfid(String cdpfid);
	
	@Transactional
	@Modifying
	@Query("update CitizenGuardianPhotos set citizenId = :newCitizenId where cdpfid = :cdpfid")
	void updateByCdpfid(@Param("cdpfid") String cdpfid, @Param("newCitizenId") String newCitizenId);

}
