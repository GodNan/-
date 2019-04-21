package com.css.bdpfnew.service.bdpfnet;

import com.css.bdpfnew.model.dto.DtoWebHospital;
import com.css.bdpfnew.model.entity.bdpfnet.NetHospital;
import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBook;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author lvmn
 * @Date 2018/12/16 21:00
 * @Description
 */
public interface NetHospitalService extends BaseService<NetHospital, Long> {
    void save(DtoWebHospital dtoWebHospital);

    NetHospital findByUuid(String uuid);
}
