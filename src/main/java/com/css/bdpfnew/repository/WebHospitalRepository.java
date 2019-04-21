package com.css.bdpfnew.repository;


import java.util.List;

import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;


public interface WebHospitalRepository extends BaseRepository<WebHospital, Long> {
	WebHospital findByUuid(String uuid);
	WebHospital findByOrgCode(String orgCode);
	List<WebHospital> findByAreaCodeLike(String areaCode);
	
}
