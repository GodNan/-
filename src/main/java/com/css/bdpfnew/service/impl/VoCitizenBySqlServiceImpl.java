package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenBySql;
import com.css.bdpfnew.repository.CitizenCardRepository;
import com.css.bdpfnew.repository.VoCitizenBySqlRepository;
import com.css.bdpfnew.service.VoCitizenBySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lvmn
 * @Date 2019/1/3 0:42
 * @Description
 */
@Service
public class VoCitizenBySqlServiceImpl extends BaseServiceImpl<CitizenBySql, Long> implements VoCitizenBySqlService {
    @Autowired
    private VoCitizenBySqlRepository voCitizenBySqlRepository;

    @Autowired
    public void setBaseDao(VoCitizenBySqlRepository voCitizenBySqlRepository) {
        super.setBaseDao(voCitizenBySqlRepository);
    }

}
