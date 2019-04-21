/**
 * 
 */
package com.css.bdpfnew.repository.idt;

import com.css.bdpfnew.model.entity.bdpfnew.IdtPhotosReup;
import com.css.bdpfnew.repository.BaseRepository;



public interface IdtPhotoReupRepository extends BaseRepository<IdtPhotosReup, Long> {
	IdtPhotosReup findByCitizenId(String citizenId);
}
