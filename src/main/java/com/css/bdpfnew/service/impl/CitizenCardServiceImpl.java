package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenCardStatus;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.repository.CitizenCardRepository;
import com.css.bdpfnew.repository.ExcelRepository;
import com.css.bdpfnew.service.CitizenCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenCardServiceImpl extends BaseServiceImpl<CitizenCardStatus, Long> implements CitizenCardService {
    @Autowired
    private CitizenCardRepository citizenCardRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    public void setBaseDao(CitizenCardRepository citizenCardRepository) {
        super.setBaseDao(citizenCardRepository);
    }

    @Override
    public List<Object[]> findByVoCredentials(VoCredentials voCredentials) {
        return excelRepository.findByVoCredentials(voCredentials);
    }
}
