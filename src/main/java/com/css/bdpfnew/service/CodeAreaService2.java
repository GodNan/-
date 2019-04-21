package com.css.bdpfnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.service.redis.CodeAreaRedis;

@Service
public class CodeAreaService2 {
	@Autowired
	private AreaRepository codeAreaRepository;
	@Autowired
	private CodeAreaRedis codeAreaRedis;
	private static final String keyHead="com.css.bdpfnew.service.impl.CodeAreaServiceImplfindCodeAreaByAreaCode";
	
	public CodeAreaEntity findByAreaCode(String areaCode){
		CodeAreaEntity codeArea=codeAreaRedis.get(keyHead+areaCode);
		if(codeArea == null){
			if(codeArea != null){
			codeAreaRedis.add(keyHead+areaCode, 1L, codeArea);	
			}
		}
		return codeArea;
	}
	
	public CodeAreaEntity create(CodeAreaEntity codeArea){
		CodeAreaEntity newCodeArea=codeAreaRepository.save(codeArea);
		if(newCodeArea!=null){
			codeAreaRedis.add(keyHead+newCodeArea.getAreaCode(), 1L, newCodeArea);
		}
		return newCodeArea;
	}
	
	public CodeAreaEntity update(CodeAreaEntity codeArea){
		if(codeArea!=null){
			codeAreaRedis.delete(keyHead+codeArea.getAreaCode());
			codeAreaRedis.add(keyHead+codeArea.getAreaCode(), 1L, codeArea);
		}
		return codeAreaRepository.save(codeArea);
	}
	
	public void delete(String areaCode){
		codeAreaRedis.delete(areaCode);
	}
	

}
