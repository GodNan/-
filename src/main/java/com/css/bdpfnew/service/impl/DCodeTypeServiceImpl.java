package com.css.bdpfnew.service.impl;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoDCodeType;
import com.css.bdpfnew.model.entity.bdpfnew.DCodeType;
import com.css.bdpfnew.model.vo.VoDCodeType;
import com.css.bdpfnew.repository.DCodeTypeRepository;
import com.css.bdpfnew.service.DCodeTypeService;

import java.util.ArrayList;
import java.util.List;




@Service
public class DCodeTypeServiceImpl implements DCodeTypeService {
	@Autowired
	DCodeTypeRepository dCodeTypeRepository;

	@Override
	@Cacheable(value = "codetype",keyGenerator="keyGenerator")
	public DCodeType findByTypeCode(String typeCode) {
		return dCodeTypeRepository.findByTypeCode(typeCode);
	}

	@Override
	@CacheEvict(value = {"codetype","codetypes"},keyGenerator="keyGenerator",allEntries = true)
	public void update(DtoDCodeType dtoCodeType) {
		 DCodeType dCodeType=new DCodeType();
		 BeanUtils.copyProperties(dtoCodeType, dCodeType);
		 dCodeTypeRepository.save(dCodeType);
	}
	
	@Override
	@CacheEvict(value = {"codetype","codetypes"},keyGenerator="keyGenerator",allEntries = true)
	public void save(DtoDCodeType dtoCodeType) {
		 DCodeType dCodeType=new DCodeType();
		 BeanUtils.copyProperties(dtoCodeType, dCodeType);
		 dCodeTypeRepository.save(dCodeType);
	}
	
	@Override
	@CacheEvict(value = {"codetype","codetypes"},keyGenerator="keyGenerator",allEntries = true)
	public void delete(String uuid) {
		 dCodeTypeRepository.delete(uuid);
	}
	
	@Override
	@Cacheable(value = "codetypes",keyGenerator="keyGenerator")
	public List<VoDCodeType> findByTypeCodeOrDescription(String typeCode,String description) {
		List<Object[]> list=dCodeTypeRepository.findByTypeCodeLikeOrDescriptionLike(typeCode, description);
		List<VoDCodeType> voDodeTypes=new ArrayList<VoDCodeType>();
		for(Object[] object:list){
			VoDCodeType voDCodeType=new VoDCodeType();
			voDCodeType.setTypeCode((String) object[0]);
			voDCodeType.setDescription((String)object[1]);
			voDCodeType.setRemark((String)object[2]);
			voDCodeType.setFlag(Integer.parseInt(object[3].toString()));
			voDCodeType.setContents((String)object[4]);
			voDodeTypes.add(voDCodeType);
		}
		return voDodeTypes;
		//return dCodeTypeRepository.findByTypeCodeLikeOrDescriptionLike(typeCode, description);
	}
		
		

	@Override
	@Cacheable(value = "codetypes",keyGenerator="keyGenerator")
	public List<DCodeType> findAll() {
		return dCodeTypeRepository.findAll();
	}

	@Override
	public List<VoDCodeType> findAlls() {
		List<Object[]> list=dCodeTypeRepository.findAlls();
		List<VoDCodeType> voDodeTypes=new ArrayList<VoDCodeType>();
		for(Object[] object:list){
			VoDCodeType voDCodeType=new VoDCodeType();
			voDCodeType.setTypeCode((String) object[0]);
			voDCodeType.setDescription((String)object[1]);
			voDCodeType.setRemark((String)object[2]);
			voDCodeType.setFlag(Integer.parseInt(object[3].toString()));
			voDCodeType.setContents((String)object[4]);
			voDodeTypes.add(voDCodeType);
		}
		return voDodeTypes;
	}
    
}
