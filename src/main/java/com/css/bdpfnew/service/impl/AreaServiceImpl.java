package com.css.bdpfnew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoCodeArea;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.service.AreaService;

@Service
public class AreaServiceImpl extends BaseServiceImpl<CodeAreaEntity, Long> implements AreaService {

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	public void setBaseDao(AreaRepository areaRepository) {
		super.setBaseDao(areaRepository);
	}

	/**
	 * 获取区域列表
	 * 
	 * @return List<CodeAreaEntity>
	 */
	// @Override
	// public List<CodeAreaEntity> getAreaList() {
	// return areaRepository.queryArea();
	// }

	/**
	 * 根据父区划查询子区划列表
	 * 
	 * @return List<CodeAreaEntity>
	 */
	@Override
	public	List<CodeAreaEntity> getAreaListByParent(String parentId){
		return areaRepository.listAreaByParent(parentId);
	}

	@Override
	public List<Map<String, Object>> lableValueAreaByParent(String parentId) {
		return areaRepository.lableValueAreaByParent(parentId);
	}

	/**
	 * 查询区县
	 *
	 * @return List<CodeAreaEntity>
	 */
	@Override
	public List<CodeAreaEntity> getQuxians() {
		return areaRepository.getQuxians();
	}

	@Override
	public CodeAreaEntity findByUuid(String uuid) {
		return areaRepository.findByUuid(uuid);
	}
	
	@Override
	public CodeAreaEntity findByAreaCode(String areaCode) {
		return areaRepository.findByAreaCode(areaCode);
	}

	@Override
	@Transactional
	public String update(DtoCodeArea dtoCodeArea) {
		CodeAreaEntity area = areaRepository.findByUuid(dtoCodeArea.getUuid());
		BeanUtils.copyProperties(dtoCodeArea, area, new String[] { "uuid" });
		area = areaRepository.save(area);

		System.out.println("update - area.getUuid():" + area.getUuid());
		return area.getUuid();
	}
	
}
