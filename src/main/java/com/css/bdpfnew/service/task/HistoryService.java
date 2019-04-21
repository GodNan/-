/**
 * 
 */
package com.css.bdpfnew.service.task;

import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.vo.VoRequest;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年10月10日 下午4:46:36
 * @Description
 */
public interface HistoryService extends BaseService<History, Long> {
	VoRequest findByUuid(String uuid);

	VoRequest findByRequestId(String uuid);
}