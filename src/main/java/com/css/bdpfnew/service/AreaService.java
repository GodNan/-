package com.css.bdpfnew.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoCodeArea;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;

public interface AreaService extends BaseService<CodeAreaEntity, Long> {

	/**
	 * 获取区域列表
	 * 
	 * @return List<CodeAreaEntity>
	 */
	//List<CodeAreaEntity> getAreaList();
	
	/**
	 * 根据父区划查询子区划列表
	 * 
	 * @return List<CodeAreaEntity>
	 */
	List<CodeAreaEntity> getAreaListByParent(String parentId);

	/**
	 * 查询区县
	 *
	 * @return List<CodeAreaEntity>
	 */
	List<CodeAreaEntity> getQuxians();

	CodeAreaEntity findByUuid(String uuid);
	
	CodeAreaEntity findByAreaCode(String areaCode);
	
	@Transactional
	String update(DtoCodeArea dtoCodeArea);


	/**
	 * 查询 value label 格式子区划
	 * @param parentId
	 * @return
	 */
    List<Map<String, Object>> lableValueAreaByParent(String parentId);
}
