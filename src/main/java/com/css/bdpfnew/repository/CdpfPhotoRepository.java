/**
 * 
 */
package com.css.bdpfnew.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;

/**
 * @Author erDuo
 * @Date 2018年9月20日 下午3:20:38
 * @Description
 */

public interface CdpfPhotoRepository extends BaseRepository<CdpfPhoto, Long> {
	CdpfPhoto findByUuid(String uuid);

	CdpfPhoto findByCdpfid(String cdpfid);
	
	CdpfPhoto findByCitizenId(String citizenId);
	@Transactional
	@Modifying
	@Query(value = "update bdpfnew.CDPF_PHOTOS t set t.image_idt =:oldPhoto where t.citizen_id = :citizenId ", nativeQuery = true)
    void updateImageIdt(@Param("oldPhoto") byte[] oldPhoto, @Param("citizenId") String citizenId);
	@Transactional
	@Modifying
	@Query("update CdpfPhoto set citizenId = :newCitizenId where citizenId = :oldCitizenId")
	void updateByCitizenId(@Param("oldCitizenId") String oldCitizenId, @Param("newCitizenId") String newCitizenId);
}
