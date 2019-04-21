/**
 * 
 */
package com.css.bdpfnew.service.idt;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoIdt;

/**
 * @Author erDuo
 * @Date 2019年3月24日 下午3:45:43
 * @Description
 */
public interface IdtNoPassedService {
	@Transactional
	String idtNoPassedUpdate(DtoIdt dtoIdt);
}
