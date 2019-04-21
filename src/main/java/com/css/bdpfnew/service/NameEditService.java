/**
 * 
 */
package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoNameEditParams;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;

/**
 * @Author HEYCH
 * @Date 2019年1月27日 下午2:16:45
 * @Description
 */
public interface NameEditService extends BaseService<CdpfCitizen, Long> {

	@Transactional
	String nameEdit(DtoNameEditParams dtoNameEdit);

}
