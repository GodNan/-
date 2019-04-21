/**
 * 
 */
package com.css.bdpfnew.service.impl.idt;
import com.css.bdpfnew.model.entity.bdpfnew.IdtPhotosReup;
import com.css.bdpfnew.repository.idt.IdtPhotoReupRepository;
import com.css.bdpfnew.service.idt.IdtPhotoReupService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class IdtPhotoReupServiceImpl extends BaseServiceImpl<IdtPhotosReup, Long> implements IdtPhotoReupService {

	@Autowired
	private IdtPhotoReupRepository idtPhotoReupRepository;

	@Autowired
	public void setBaseDao(IdtPhotoReupRepository idtPhotoReupRepository) {
		super.setBaseDao(idtPhotoReupRepository);
	}


	@Override
	public IdtPhotosReup findByCitizenId(String citizenId) {
		return idtPhotoReupRepository.findByCitizenId(citizenId);
	}

	@Override
	@Transactional
	public String save(IdtPhotosReup idtPhotosReup) {
		IdtPhotosReup idtPhotosReup1=idtPhotoReupRepository.save(idtPhotosReup);
		return idtPhotosReup1.getUuid();
	}
}
