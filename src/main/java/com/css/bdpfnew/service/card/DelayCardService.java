package com.css.bdpfnew.service.card;

import java.text.ParseException;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author HEYCH
 * @Date 2018年10月28日 
 * @Description:
 */

public interface DelayCardService {

	@Transactional
	boolean update(String requestIdCard) throws ParseException;
}
