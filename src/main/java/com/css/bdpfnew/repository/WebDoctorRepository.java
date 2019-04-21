package com.css.bdpfnew.repository;


import com.css.bdpfnew.model.entity.bdpfnew.WebDoctor;


public interface WebDoctorRepository extends BaseRepository<WebDoctor, Long> {
	WebDoctor findByUuid(String uuid);
}
