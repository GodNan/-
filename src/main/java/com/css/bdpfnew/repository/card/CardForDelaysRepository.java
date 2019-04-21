/**
 * 
 */
package com.css.bdpfnew.repository.card;

import com.css.bdpfnew.model.entity.bdpfnew.view.CardForDelays;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:34:34
 * @Description
 */
public interface CardForDelaysRepository extends BaseRepository<CardForDelays, Long> {

	Integer countByCityidLike(String cityid);

}
