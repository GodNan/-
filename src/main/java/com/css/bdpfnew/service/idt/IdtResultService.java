package com.css.bdpfnew.service.idt;

import com.css.bdpfnew.model.entity.bdpfnew.IdtResult;
import com.css.bdpfnew.model.vo.VoIdtResult;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年8月1日 下午3:59:29
 * @Description:
 */
public interface IdtResultService extends BaseService<IdtResult, Long> {

	VoIdtResult findByUuid(String uuid);

	VoIdtResult findByRequestId(String requestId);

}
