package com.css.bdpfnew.service;

import com.css.bdpfnew.model.dto.DtoWebHospital;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WebHospitalService extends BaseService<WebHospital, Long> {
	WebHospital findByUuid(String uuid);
	
	WebHospital findByOrgCode(String orgCode);
	
	@Transactional
	String update(DtoWebHospital dtoWebHospital);
	
	@Transactional
	void save(DtoWebHospital dtoWebHospital);
	
	List<WebHospital> findAll();
	
	List<WebHospital> findByAreaCode(String areaCode);

}
