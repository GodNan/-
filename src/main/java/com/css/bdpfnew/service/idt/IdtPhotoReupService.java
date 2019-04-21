/**
 * 
 */
package com.css.bdpfnew.service.idt;

import com.css.bdpfnew.model.entity.bdpfnew.IdtPhotosReup;
import com.css.bdpfnew.service.BaseService;
import org.springframework.transaction.annotation.Transactional;


public interface IdtPhotoReupService extends BaseService<IdtPhotosReup, Long> {

	IdtPhotosReup findByCitizenId(String citizenId);
    @Transactional
    String save(IdtPhotosReup idtPhotosReup);
}
