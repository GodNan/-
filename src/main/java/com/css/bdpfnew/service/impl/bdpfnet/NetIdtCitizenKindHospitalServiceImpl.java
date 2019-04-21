package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.dto.DtoCitizenKindHospital;
import com.css.bdpfnew.model.entity.bdpfnet.NetIdtCitizenKindHospital;
import com.css.bdpfnew.repositorynet.ExcelRepository;
import com.css.bdpfnew.repositorynet.NetIdtCitizenKindHospitalRepository;
import com.css.bdpfnew.service.bdpfnet.NetIdtCitizenKindHospitalService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetIdtCitizenKindHospitalServiceImpl extends BaseServiceImpl<NetIdtCitizenKindHospital, Long> implements NetIdtCitizenKindHospitalService {

	@Autowired
	private NetIdtCitizenKindHospitalRepository netIdtCitizenKindHospitalRepository;
	@Autowired
	public void setBaseDao(NetIdtCitizenKindHospitalRepository netIdtCitizenKindHospitalRepository) {
		super.setBaseDao(netIdtCitizenKindHospitalRepository);
	}

	@Autowired
	private ExcelRepository excelRepository;

	/**
	 * 根据条件查找需要导出的预约医院的残疾人数据
	 * @param dtoCitizenKindHospital
	 * @return
	 */
	@Override
	public List<Object[]> findByCondition(DtoCitizenKindHospital dtoCitizenKindHospital) {
		return excelRepository.findByCondition(dtoCitizenKindHospital);
	}
}
