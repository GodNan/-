/**
 * 
 */
package com.css.bdpfnew.service;

import com.css.bdpfnew.model.entity.bdpfnew.GonganData;

/**
 * @Author erDuo
 * @Date 2018年12月23日 上午10:11:40
 * @Description
 */

public interface GonganDataService extends BaseService<GonganData, Long> {
	GonganData findByCitizenId(String citizenId);
}
