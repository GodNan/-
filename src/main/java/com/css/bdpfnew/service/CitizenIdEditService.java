/**
 * 
 */
package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoCitizenIdEditParams;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;

/**
 * @Author HEYCH
 * @Date 2019年2月26日 下午2:16:45
 * @Description
 */
public interface CitizenIdEditService extends BaseService<CdpfCitizen, Long> {

	@Transactional
	String citizenIdEdit(DtoCitizenIdEditParams dtoNameEdit);

}
