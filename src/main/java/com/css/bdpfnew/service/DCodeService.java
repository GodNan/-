package com.css.bdpfnew.service;

import java.util.List;

import com.css.bdpfnew.model.dto.DtoDCode;
import com.css.bdpfnew.model.entity.bdpfnew.DCode;

public interface DCodeService {

	DCode findByUuid(String uuid);
	
	DCode findByCodeAndTypeCode(String code, String typeCode);

	List<DCode> findByTypeCode(String typeCode);

	List<DCode> findByTypeCodeAndRemark(String typeCode, String remark);

	void update(DtoDCode dtoCode);

	void delete(String uuid);

	void save(DtoDCode dtoCode);

	List<DCode> findByCodeAndDescription(String code, String description);

	List<DCode> findAll();

    List<String> findCardStatus();
}
