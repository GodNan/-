/**
 * 
 */
package com.css.bdpfnew.repository.card;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author HEYCH
 * @Date 2018年10月18日 下午4:19:16
 * @Description
 */

public interface CardCheckRepository extends BaseRepository<CdpfCitizen, Long> {
	CdpfCitizen findByCitizenId(String citizenId);
}
