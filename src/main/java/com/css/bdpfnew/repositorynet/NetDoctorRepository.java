package com.css.bdpfnew.repositorynet;


import com.css.bdpfnew.model.entity.bdpfnet.NetDoctor;
import com.css.bdpfnew.model.entity.bdpfnew.WebDoctor;
import com.css.bdpfnew.repository.BaseRepository;


public interface NetDoctorRepository extends BaseRepository<NetDoctor, Long> {
	NetDoctor findByUuid(String uuid);

    NetDoctor findByCitizenId(String citizenId);
}
