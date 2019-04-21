/**
 * 
 */
package com.css.bdpfnew.repository;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenZhuxiaoKindDate;

/**
 * @Author erDuo
 * @Date 2019年3月7日 上午10:58:10
 * @Description
 */
public interface CitizenZhuxiaoKindDateRepository extends BaseRepository<CitizenZhuxiaoKindDate, Long> {
	CitizenZhuxiaoKindDate findByCdpfIdAndIdtKind(String cdpfId, Integer idtKind);
	List<CitizenZhuxiaoKindDate> findByCdpfId(String cdpfId);
}
