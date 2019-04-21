package com.css.bdpfnew.service.impl;
import com.css.bdpfnew.model.entity.bdpfnew.view.VwMigration;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.repository.CitizenForMigrationRepository;
import com.css.bdpfnew.repository.ExcelRepository;
import com.css.bdpfnew.service.CitizenForMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CitizenForMigrationServiceImpl extends BaseServiceImpl<VwMigration, Long> implements CitizenForMigrationService {

	@Autowired
	public void setBaseDao(CitizenForMigrationRepository citizenForMigrationRepository) {
		super.setBaseDao(citizenForMigrationRepository);
	}
	@Autowired
	private ExcelRepository excelRepository;

}
