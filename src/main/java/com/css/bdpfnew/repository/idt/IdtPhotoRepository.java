/**
 * 
 */
package com.css.bdpfnew.repository.idt;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenIdtPhoto;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年10月29日 上午10:43:45
 * @Description
 */
public interface IdtPhotoRepository extends BaseRepository<CitizenIdtPhoto, Long> {
	
	CitizenIdtPhoto findByUuid(String uuid);

	CitizenIdtPhoto findByIdtRecordUuidAndCitizenIdAndIdtkind(String idtRecordUuid, String citizenId, Integer idtKind);

	List<CitizenIdtPhoto> findByCitizenId(String citizenId);
	
	@Transactional
	@Modifying
	@Query("update CitizenIdtPhoto set citizenId = :newCitizenId where citizenId = :oldCitizenId")
	void updateByCitizenId(@Param("oldCitizenId") String oldCitizenId, @Param("newCitizenId") String newCitizenId);
	
}
