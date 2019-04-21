package com.css.bdpfnew.service.bdpfnet;

import com.css.bdpfnew.model.dto.DtoWebDoctor;
import com.css.bdpfnew.model.entity.bdpfnet.NetDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebDoctor;
import com.css.bdpfnew.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

public interface NetDoctorService extends BaseService<NetDoctor, Long> {
	
	@Transactional
	void save(DtoWebDoctor dtoWebDoctor);
	
}
