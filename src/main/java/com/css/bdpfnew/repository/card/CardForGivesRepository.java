/**
 * 
 */
package com.css.bdpfnew.repository.card;

import com.css.bdpfnew.model.entity.bdpfnew.view.CardForGives;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:35:21
 * @Description
 */
public interface CardForGivesRepository extends BaseRepository<CardForGives, Long> {

	Integer countByCityidLike(String cityid);

}
