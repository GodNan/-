package com.css.bdpfnew.service.bdpfnet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBook;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author lvmn
 * @Date 2018/12/16 21:00
 * @Description
 */
public interface NetHospitalBookService extends BaseService<NetHospitalBook, Long> {
    void save(NetHospitalBook netHospitalBook);

    NetHospitalBook findByHospitalIdAndIdt(String uuid, Integer id);
}
