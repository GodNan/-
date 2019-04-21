package com.css.bdpfnew.repositorynet;


import com.css.bdpfnew.model.entity.bdpfnet.NetIdtCitizenKindHospital;
import com.css.bdpfnew.repository.BaseRepository;


public interface NetIdtCitizenKindHospitalRepository extends BaseRepository<NetIdtCitizenKindHospital, Long> {
    NetIdtCitizenKindHospital findByCitizenId(String uuid);
}
