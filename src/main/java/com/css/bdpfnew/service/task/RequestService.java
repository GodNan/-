package com.css.bdpfnew.service.task;

import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.vo.VoRequest;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午11:11:51
 * @Description:
 */
public interface RequestService extends BaseService<Request, Long> {
	VoRequest findByUuid(String uuid);
}
