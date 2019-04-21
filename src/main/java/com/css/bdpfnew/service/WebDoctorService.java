package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoWebDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebDoctor;

public interface WebDoctorService extends BaseService<WebDoctor, Long> {
	
	WebDoctor findByUuid(String uuid);
	
	@Transactional
	String update(DtoWebDoctor dtoWebDoctor);
	
	@Transactional
	String save(DtoWebDoctor dtoWebDoctor);
	
	String GetHosEvalTypeArr(String workHospital);
}
