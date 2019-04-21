package com.css.bdpfnew.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoDCode;
import com.css.bdpfnew.model.entity.bdpfnew.DCode;
import com.css.bdpfnew.repository.DCodeRepository;
import com.css.bdpfnew.service.DCodeService;

@Service
public class DCodeServiceImpl implements DCodeService {
	@Autowired
	DCodeRepository dCodeRepository;

	@Override
	@Cacheable(value = "code", keyGenerator = "keyGenerator")
	public DCode findByUuid(String uuid) {
		return dCodeRepository.findByUuid(uuid);
	}
	
	@Override
	@Cacheable(value = "code", keyGenerator = "keyGenerator")
	public DCode findByCodeAndTypeCode(String code, String typeCode) {
		return dCodeRepository.findByCodeAndTypeCode(code, typeCode);
	}
	
	@Override
	@Cacheable(value = "codes", keyGenerator = "keyGenerator")
	public List<DCode> findByTypeCode(String typeCode) {
		return dCodeRepository.findByTypeCode(typeCode);
	}

	@Override
	@Cacheable(value = "codes", keyGenerator = "keyGenerator")
	public List<DCode> findByTypeCodeAndRemark(String typeCode, String remark) {
		return dCodeRepository.findByTypeCodeAndRemark(typeCode, remark);
	}

	@Override
	public void update(DtoDCode dtoCode) {
		DCode dCode = new DCode();
		BeanUtils.copyProperties(dtoCode, dCode);
		dCodeRepository.save(dCode);
	}

	@Override
	public void delete(String uuid) {
		dCodeRepository.delete(uuid);
	}

	@Override
	public void save(DtoDCode dtoCode) {
		DCode dCode = new DCode();
		BeanUtils.copyProperties(dtoCode, dCode);
		dCodeRepository.save(dCode);
	}

	@Override
	@Cacheable(value = "codes", keyGenerator = "keyGenerator")
	public List<DCode> findByCodeAndDescription(String code, String description) {
		return dCodeRepository.findByCodeLikeAndDescriptionLike(code, description);
	}

	@Override
	@Cacheable(value = "codes", keyGenerator = "keyGenerator")
	public List<DCode> findAll() {
		return dCodeRepository.findAll();
	}

	@Override
	public List<String> findCardStatus() {
		List<String> list = dCodeRepository.findDescriptionByTypeCode("d_card_status");
		return list;
	}

}
