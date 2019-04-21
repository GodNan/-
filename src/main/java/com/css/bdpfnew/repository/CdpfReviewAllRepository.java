/**
 * 
 */
package com.css.bdpfnew.repository;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfReviewAll;

/**
 * @Author erDuo
 * @Date 2019年1月6日 下午5:51:57
 * @Description
 */

public interface CdpfReviewAllRepository extends BaseRepository<CdpfReviewAll, Long> {

	List<CdpfReviewAll> findByCitizenId(String citizenId);
}