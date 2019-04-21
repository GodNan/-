/**
 * 
 */
package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.GonganData;

/**
 * @Author erDuo
 * @Date 2018年12月23日 上午10:01:50
 * @Description
 */

public interface GonganDataRepository extends BaseRepository<GonganData, Long> {
	GonganData findByCitizenId(String citizenId);
}
