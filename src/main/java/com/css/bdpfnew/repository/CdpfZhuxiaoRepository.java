/**
 * 
 */
package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfZhuxiao;

/**
 * @Author erDuo
 * @Date 2018年12月7日 下午5:15:21
 * @Description
 */
public interface CdpfZhuxiaoRepository extends BaseRepository<CdpfZhuxiao, Long> {

	CdpfZhuxiao findByCitizenId(String citizenId);
}
