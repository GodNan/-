package com.css.bdpfnew.repositorynet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospital;
import com.css.bdpfnew.repository.BaseRepository;

/**
 * @Author lvmn
 * @Date 2018/12/19 16:39
 * @Description
 *  互联网医院
 */
public interface NetHospitalRepository extends BaseRepository<NetHospital, Integer> {
    NetHospital findByUuid(String uuid);
}
