/**
 * 
 */
package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoCdpfZhuxiao;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfZhuxiao;

/**
 * @Author erDuo
 * @Date 2018年12月7日 下午5:29:43
 * @Description
 */

public interface ZhuxiaoService extends BaseService<CdpfZhuxiao, Long> {
	@Transactional
	String noLogout(DtoCdpfZhuxiao dtoCdpfZhuxiao);

	@Transactional
	String unlock(DtoCdpfZhuxiao dtoCdpfZhuxiao);

	@Transactional
	String lock(DtoCdpfZhuxiao dtoCdpfZhuxiao);

}
