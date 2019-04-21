/**
 * 
 */
package com.css.bdpfnew.repository.card;

import com.css.bdpfnew.model.entity.bdpfnew.view.CardForPhotos;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年9月26日 下午4:01:29
 * @Description
 */

public interface CardForPhotosRepository extends BaseRepository<CardForPhotos, Long> {

//	@Query(value = "select count(1) from CardForPhotos c where c.cityid like :cityid")
//	Integer countCardForPhotosByCityid(@Param("cityid") String cityid);

	Integer countByCityidLike(String cityid);
	
	CardForPhotos findByCitizenId(String citizenId);
}