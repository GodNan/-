package com.css.bdpfnew.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.service.CodeAreaService;
@Service
public class CodeAreaServiceImpl implements CodeAreaService{
	@Autowired
	AreaRepository codeAreaRepository;
	@Override
	@Cacheable(value = "codeAreas",keyGenerator="keyGenerator")
	public List<CodeAreaEntity> getCodeAreaList() {
		// TODO Auto-generated method stub
		return codeAreaRepository.findAll();
	}

	@Override
	@Cacheable(value="codeArea",keyGenerator="keyGenerator")
	public CodeAreaEntity findCodeAreaByAreaCode(String areaCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeAreaEntity save(CodeAreaEntity codeArea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@CacheEvict(value = {"codeArea","codeAreas"},keyGenerator="keyGenerator",allEntries = true)
	public CodeAreaEntity edit(CodeAreaEntity codeArea) {
		// TODO Auto-generated method stub
		return codeAreaRepository.save(codeArea);
	}

	@Override
	public String delete(String areaCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
