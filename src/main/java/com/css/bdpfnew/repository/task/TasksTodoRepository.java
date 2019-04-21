package com.css.bdpfnew.repository.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.view.TasksTodo;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author erDuo
 * @Date 2018年6月19日 下午3:11:18
 * @Description:
 */

public interface TasksTodoRepository extends BaseRepository<TasksTodo, Long> {

	@Query(value = "select count(1) from TasksTodo h where h.cityidHolder like :cityidHolder and h.permissionId in :permissions")
	Integer countTasksToDoByCityAndPermissions(@Param("cityidHolder") String cityidHolder,
			@Param("permissions") String[] permissions);
	
	@Query(value = "select count(1) from TasksTodo h where h.cityidHolder like :cityidHolder and h.permissionId in :permissions and h.cityidRole in :cityidRoles")
	Integer countTasksToDoByCityAndPermissionsAndCityidRole(@Param("cityidHolder") String cityidHolder,
			@Param("permissions") String[] permissions,
			@Param("cityidRoles") String[] cityidRoles);

}
