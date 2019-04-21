package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoIdt;

/**
 * @Author HEYCH
 * @Date 2018年11月21日 下午13:58:20
 * @Description:
 */
public interface KsqrService {

	@Transactional
	String update(DtoIdt dtoIdt);
}
