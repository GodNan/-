package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.dto.DtoWebHospital;
import com.css.bdpfnew.model.entity.bdpfnew.WebHospital;
import com.css.bdpfnew.repository.WebHospitalRepository;
import com.css.bdpfnew.repositorynet.NetHospitalRepository;
import com.css.bdpfnew.service.WebHospitalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class WebHospitalServiceImpl extends BaseServiceImpl<WebHospital, Long> implements WebHospitalService {

	@Autowired
	private WebHospitalRepository hospitalRepository;
	@Autowired
	private NetHospitalRepository netHospitalRepository;

	@Autowired
	public void setBaseDao(WebHospitalRepository hospitalRepository) {
		super.setBaseDao(hospitalRepository);
	}

	@Override
	public WebHospital findByUuid(String uuid) {
		return hospitalRepository.findByUuid(uuid);
	}
	
	@Override
	public WebHospital findByOrgCode(String OrgCode) {
		return hospitalRepository.findByOrgCode(OrgCode);
	}
	
	@Override
	public String update(DtoWebHospital dtoWebHospital) {
		WebHospital hospital = hospitalRepository.findByUuid(dtoWebHospital.getUuid());
		BeanUtils.copyProperties(dtoWebHospital, hospital, new String[] { "uuid" });
		hospital = hospitalRepository.save(hospital);

		System.out.println("update - hospital.getUuid():" + hospital.getUuid());
		return hospital.getUuid();
	}
	
	@Override
	@Transactional
	public void save(DtoWebHospital dtoWebHospital) {
		WebHospital hospital = new WebHospital();
		BeanUtils.copyProperties(dtoWebHospital, hospital);
		// 将评残项目转坏为逗号拼接的字符串
		String[] evaluateTypes = dtoWebHospital.getEvaluateTypes();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < evaluateTypes.length; i++) {
			stringBuffer.append(evaluateTypes[i]);
			stringBuffer.append(",");
		}
		String s = stringBuffer.toString();
		hospital.setEvaluateType(s.substring(0,s.length()-1));
		// 如果选择多个街道，将街道数组转化为，拼接的字符串
		if(dtoWebHospital.getAreaCodeJiedao().size() > 0){
			StringBuffer areaCodeStringBuffer = new StringBuffer();
			Set<String> areaCodeJiedaos = dtoWebHospital.getAreaCodeJiedao();
			for (String areaCodeJiedao: areaCodeJiedaos) {
				areaCodeStringBuffer.append(areaCodeJiedao);
				areaCodeStringBuffer.append(",");
			}
			String areaCodeString = areaCodeStringBuffer.toString();
			hospital.setAreaCode(areaCodeString.substring(0,areaCodeString.length()-1));
		}
		hospitalRepository.save(hospital);
	}

	@Override
	public List<WebHospital> findAll() {
		List<WebHospital> webHospitals = hospitalRepository.findAll();
        return webHospitals;
	}

	@Override
	public List<WebHospital> findByAreaCode(String areaCode) {
		List<WebHospital> webHospitals = hospitalRepository.findByAreaCodeLike(areaCode);
		return webHospitals;
	}

}
