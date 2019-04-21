package com.css.bdpfnew.service.card;

import java.text.ParseException;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author HEYCH
 * @Date 2018年10月28日 
 * @Description:
 */

public interface CardGiveService {

	@Transactional
	boolean update(String requestIdCard, Integer check260, String cardNoFlagT, String newCardNo) throws ParseException;
}
