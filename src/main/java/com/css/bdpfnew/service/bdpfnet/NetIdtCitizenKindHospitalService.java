package com.css.bdpfnew.service.bdpfnet;

import com.css.bdpfnew.model.dto.DtoCitizenKindHospital;
import com.css.bdpfnew.model.entity.bdpfnet.NetIdtCitizenKindHospital;
import com.css.bdpfnew.service.BaseService;

import java.util.List;

public interface NetIdtCitizenKindHospitalService extends BaseService<NetIdtCitizenKindHospital, Long> {

    List<Object[]> findByCondition(DtoCitizenKindHospital dtoCitizenKindHospital);
}
