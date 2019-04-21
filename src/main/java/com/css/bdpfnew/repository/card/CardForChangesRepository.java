/**
 * 
 */
package com.css.bdpfnew.repository.card;

import com.css.bdpfnew.model.entity.bdpfnew.view.CardForChanges;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年9月28日 上午10:04:39
 * @Description
 */

public interface CardForChangesRepository extends BaseRepository<CardForChanges, Long> {

//	@Query(value = "select count(1) from CardForPhotos c where c.cityid like :cityid")
//	Integer countCardForPhotosByCityid(@Param("cityid") String cityid);
	
	Integer countByCityidLike(String cityid);

}