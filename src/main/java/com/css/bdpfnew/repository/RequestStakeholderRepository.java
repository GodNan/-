package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.RequestStakeholder;

/**
 * @Author erDuo
 * @Date 2018年7月9日 上午11:10:26
 * @Description:
 */
public interface RequestStakeholderRepository extends BaseRepository<RequestStakeholder, Long> {

	RequestStakeholder findByRequestId(String requestId);

}
