package com.css.bdpfnew.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年6月14日 下午3:13:04
 * @Description:
 */
public interface RequestRepository extends BaseRepository<Request, Long> {

	Request findByUuid(String uuid);

	Request findByCdpfId(String cdpfId);
	
	List<Request> findByCitizenId(String citizenId);

	@Query(value = "select count(1) from RequestStakeholder h where h.cityid = :cityid and h.permissionId in :permissions")
	Integer countTasksToDoByCityAndPermissions(@Param("cityid") String uuid,
			@Param("permissions") String[] permissions);

}
