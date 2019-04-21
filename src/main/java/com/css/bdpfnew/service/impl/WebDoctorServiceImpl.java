package com.css.bdpfnew.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoWebDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.repository.WebDoctorRepository;
import com.css.bdpfnew.repository.WebHospitalRepository;
import com.css.bdpfnew.service.WebDoctorService;
import com.css.bdpfnew.util.UuidUtil;

@Service
public class WebDoctorServiceImpl extends BaseServiceImpl<WebDoctor, Long> implements WebDoctorService {

	@Autowired
	private WebDoctorRepository doctorRepository;
	@Autowired
	private WebHospitalRepository webHospitalRepository;

	@Autowired
	public void setBaseDao(WebDoctorRepository doctorRepository) {
		super.setBaseDao(doctorRepository);
	}

	@Override
	public WebDoctor findByUuid(String uuid) {
		return doctorRepository.findByUuid(uuid);
	}
	
	@Override
	public String update(DtoWebDoctor dtoWebDoctor) {
		WebDoctor doctor = doctorRepository.findByUuid(dtoWebDoctor.getUuid());
		BeanUtils.copyProperties(dtoWebDoctor, doctor, new String[] { "uuid" });
		WebHospital webHospital = webHospitalRepository.findByOrgCode(doctor.getWorkHospital());
		if(webHospital != null) {
			doctor.setHospitalId(webHospital.getUuid());
			doctor.setWorkHospitalName(webHospital.getOrgName());
			doctor.setHosEvalType(webHospital.getEvaluateType());
			doctor.setAreaCode(webHospital.getAreaCode());
		}
		doctor = doctorRepository.save(doctor);

		System.out.println("update - doctor.getUuid():" + doctor.getUuid());
		return doctor.getUuid();
	}
	
	@Override
	@Transactional
	public String save(DtoWebDoctor dtoWebDoctor) {
		String uuid = UuidUtil.getUuid();
		WebDoctor doctor = new WebDoctor();
		BeanUtils.copyProperties(dtoWebDoctor, doctor);
		doctor.setUuid(uuid);
		WebHospital webHospital = webHospitalRepository.findByOrgCode(doctor.getWorkHospital());
		if(webHospital != null) {
			doctor.setHospitalId(webHospital.getUuid());
			doctor.setWorkHospitalName(webHospital.getOrgName());
			doctor.setHosEvalType(webHospital.getEvaluateType());
			doctor.setAreaCode(webHospital.getAreaCode());
		}
		doctor = doctorRepository.save(doctor);

		System.out.println("uuid:" + uuid);
		System.out.println("doctor.getUuid():" + doctor.getUuid());

		return doctor.getUuid();
	}
	
	@Override
	public String GetHosEvalTypeArr(String workHospital) {
		WebHospital webHospital = webHospitalRepository.findByOrgCode(workHospital);
		String hosEvalTypeArr = "";
		
		if(webHospital != null){
			hosEvalTypeArr = webHospital.getEvaluateType();
		}
		
		return hosEvalTypeArr;
	}
}
