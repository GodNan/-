package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoIdt;

/**
 * @Author erDuo
 * @Date 2018年6月29日 下午4:51:02
 * @Description:
 */
public interface IdtService {
	@Transactional
	String save(DtoIdt dtoIdt);

	@Transactional
	String update(DtoIdt dtoIdt);
}
