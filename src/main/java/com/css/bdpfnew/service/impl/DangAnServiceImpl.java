package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfDangAn;
import com.css.bdpfnew.repository.DangAnRepository;
import com.css.bdpfnew.service.DangAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lvmn
 * @Date 2019/1/16 15:11
 * @Description
 */
@Service
public class DangAnServiceImpl extends BaseServiceImpl<CdpfDangAn, Long> implements DangAnService{
    @Autowired
    private DangAnRepository dangAnRepository;

    @Override
    public void save(CdpfDangAn dangAn) {
        dangAnRepository.save(dangAn);
    }

    @Override
    public CdpfDangAn findByCitizenId(String citizenId) {
        return dangAnRepository.findByCitizenId(citizenId);
    }
}
