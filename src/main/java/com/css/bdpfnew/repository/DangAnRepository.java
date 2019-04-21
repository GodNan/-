package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfDangAn;

/**
 * @Author lvmn
 * @Date 2019/1/16 15:13
 * @Description
 */
public interface DangAnRepository extends BaseRepository<CdpfDangAn, Long> {
    CdpfDangAn findByCitizenId(String citizenId);
}
