package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.dto.DtoWebDoctor;
import com.css.bdpfnew.model.entity.bdpfnet.NetDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.repository.WebHospitalRepository;
import com.css.bdpfnew.repositorynet.NetDoctorRepository;
import com.css.bdpfnew.service.bdpfnet.NetDoctorService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NetDoctorServiceImpl extends BaseServiceImpl<NetDoctor, Long> implements NetDoctorService {

	@Autowired
	private NetDoctorRepository netDoctorRepository;
	@Autowired
	private WebHospitalRepository webHospitalRepository;

	@Override
	@Transactional
	public void save(DtoWebDoctor dtoWebDoctor) {
		NetDoctor doctor = netDoctorRepository.findByCitizenId(dtoWebDoctor.getCitizenId());
		if(doctor == null){
			doctor = new NetDoctor();
		}else{
			dtoWebDoctor.setUuid(doctor.getUuid());
		}
		BeanUtils.copyProperties(dtoWebDoctor, doctor,new String[] { "userIdcode"});
		WebHospital webHospital = webHospitalRepository.findByOrgCode(doctor.getWorkHospital());
		if(webHospital != null) {
			doctor.setHospitalId(webHospital.getUuid());
			doctor.setWorkHospitalName(webHospital.getOrgName());
			doctor.setHosEvalType(webHospital.getEvaluateType());
			doctor.setAreaCode(webHospital.getAreaCode().substring(0,6));
			doctor.setAreaCodeWeb(webHospital.getAreaCode());
		}
		netDoctorRepository.save(doctor);
	}

}
