package com.css.bdpfnew.repository;

import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author erDuo
 * @Date 2018年7月5日 下午3:45:04
 * @Description:
 */
public interface StateRepository extends BaseRepository<State, Long> {
	State findByUuid(String uuid);

	List<State> findByProcessIdAndStateTypeLessThanOrderByOrderNumAsc(Integer processId, Integer stateType);

	List<State> findByProcessIdAndStateTypeAndStateCodeLessThanOrderByOrderNumAsc(Integer processId, Integer stateType,
			Integer stateCode);
	
	List<State> findByProcessIdAndStateTypeAndOrderNumLessThanOrderByOrderNumAsc(Integer processId, Integer stateType,
			Integer orderNum);

	@Query(value = "select t.name from STATE t where t.process_id = :processId order by t.state_code asc", nativeQuery = true)
    String[] findStatesByProcess(@Param("processId") Integer processId);
}
