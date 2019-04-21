
package com.css.bdpfnew.repository;



import com.css.bdpfnew.model.entity.bdpfnew.view.VwCardMake;

import java.util.List;


public interface CardPhotoRepository extends BaseRepository<VwCardMake, Long> {
	List<VwCardMake> findByBdpfidIn(List<String> strList);
}
