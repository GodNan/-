package com.css.bdpfnew.service.impl.bdpfnet;

import com.css.bdpfnew.model.entity.bdpfnet.NetHospitalBookNum;
import com.css.bdpfnew.repositorynet.NetHospitalBookNumRepository;
import com.css.bdpfnew.service.bdpfnet.NetHospitalBookNumService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2019年3月5日 16:07:01
 * @Description
 */
@Service
@Transactional
public class NetHospitalBookNumServiceImpl extends BaseServiceImpl<NetHospitalBookNum, Long> implements NetHospitalBookNumService {

    @Autowired
    private NetHospitalBookNumRepository netHospitalBookNumRepository;

    @Override
    public List<NetHospitalBookNum> findByHospitalIdAndIdtKind(String uuid, Integer id) {
        return netHospitalBookNumRepository.findByHospitalIdAndIdtKindOrderByBookNumAsc(uuid, id);
    }

    @Override
    public void save(NetHospitalBookNum bookNum) {
        netHospitalBookNumRepository.save(bookNum);
    }

    @Override
    @javax.transaction.Transactional
    public void updateBookNumByHospitalIdAndIdtKind(String uuid, Integer id) {
        netHospitalBookNumRepository.updateBookNumByHospitalIdAndIdtKind(uuid,id,null);
    }
}
