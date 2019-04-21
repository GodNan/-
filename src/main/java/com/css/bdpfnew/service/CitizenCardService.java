package com.css.bdpfnew.service;

import com.css.bdpfnew.model.entity.bdpfnew.view.CitizenCardStatus;
import com.css.bdpfnew.model.vo.query.VoCredentials;

import java.util.List;

public interface CitizenCardService extends BaseService<CitizenCardStatus, Long>{
    List<Object[]> findByVoCredentials(VoCredentials voCredentials);
}
