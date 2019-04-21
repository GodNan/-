package com.css.bdpfnew.service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfDangAn;

/**
 * @Author lvmn
 * @Date 2019/1/16 14:54
 * @Description
 */
public interface DangAnService extends BaseService<CdpfDangAn, Long>{
    void save(CdpfDangAn dangAn);

    CdpfDangAn findByCitizenId(String citizenId);
}
