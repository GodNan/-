/**
 * 
 */
package com.css.bdpfnew.service.card;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCardLog;
import com.css.bdpfnew.model.vo.VoCdpfCardLog;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午4:16:39
 * @Description
 */

public interface CardLogService extends BaseService<CdpfCardLog, Long> {
	VoCdpfCardLog findByUuid(String uuid);
}
