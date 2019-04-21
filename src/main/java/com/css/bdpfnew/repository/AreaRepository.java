package com.css.bdpfnew.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;


public interface AreaRepository extends BaseRepository<CodeAreaEntity, Long> {

	/**
	 * 列出区域列表
	 * 
	 * @return
	 */
    //@Query("select areaCode,areaName,areaLevel,parentAreaCode,areaAttribute,memo,validSign from CodeAreaEntity where areaCode like '11%' and validSign = '1' ")
    //List<CodeAreaEntity> queryArea();
    
    /**
	 * 根据父区划查询子区划列表
	 * @param
	 * @return
	 */
    @Query("select new Map(areaCode as id,areaName as name) from CodeAreaEntity where validSign = '1' and parentAreaCode = :parentId order by areaCode asc ")
    List<CodeAreaEntity> listAreaByParent(@Param("parentId") String parentId);

	CodeAreaEntity findByAreaCode(String code);

	@Query("select areaCode as value,areaName as label from CodeAreaEntity where areaCode like '11%' and validSign = '1' and parentAreaCode = :parentId order by areaCode asc ")
	List<Map<String,Object>> lableValueAreaByParent(@Param("parentId") String parentId);

    /**
	 * 查询区县
	 *
	 * @return
	 */
    @Query("select new Map(areaCode as id,areaName as name) from CodeAreaEntity where parentAreaCode = '11' and validSign = '1' order by areaCode asc ")
    List<CodeAreaEntity> getQuxians();

    //@Query("select areaCode,areaName,areaLevel,parentAreaCode,areaAttribute,memo,validSign from CodeAreaEntity where validSign = '1' and uuid = :uuid")
    //CodeAreaEntity findByUuid(@Param("uuid") String uuid);

    CodeAreaEntity findByUuid(String uuid);

}
