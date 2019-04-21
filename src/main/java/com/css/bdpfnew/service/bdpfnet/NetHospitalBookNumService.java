package com.css.bdpfnew.service.bdpfnet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBookNum;
import com.css.bdpfnew.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2019/3/5 15:44
 * @Description
 */
@Transactional
public interface NetHospitalBookNumService extends BaseService<NetHospitalBookNum, Long> {
    List<NetHospitalBookNum> findByHospitalIdAndIdtKind(String uuid, Integer id);

    void save(NetHospitalBookNum bookNum);

    @javax.transaction.Transactional
    void updateBookNumByHospitalIdAndIdtKind(String uuid, Integer id);
}
