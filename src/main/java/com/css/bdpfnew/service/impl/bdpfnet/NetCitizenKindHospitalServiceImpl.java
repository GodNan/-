package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.entity.bdpfnet.NetCitizenKindHospital;
import com.css.bdpfnew.repositorynet.NetCitizenKindHospitalRepository;
import com.css.bdpfnew.service.bdpfnet.NetCitizenKindHospitalService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetCitizenKindHospitalServiceImpl extends BaseServiceImpl<NetCitizenKindHospital, Long> implements NetCitizenKindHospitalService {

	@Autowired
	private NetCitizenKindHospitalRepository netCitizenKindHospitalRepository;
	@Autowired
	public void setBaseDao(NetCitizenKindHospitalRepository netCitizenKindHospitalRepository) {
		super.setBaseDao(netCitizenKindHospitalRepository);
	}
}
