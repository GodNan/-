package com.css.bdpfnew.repository.task;

import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年7月6日 上午11:25:55
 * @Description:
 */
public interface RequestNoteRepository extends BaseRepository<RequestNote, Long> {
	RequestNote findByUuid(String uuid);
	
	RequestNote findByRequestId(String requestId);

	RequestNote findByRequestIdAndUserIdAndLatest(String requestId, String userId, Integer latest);
}
