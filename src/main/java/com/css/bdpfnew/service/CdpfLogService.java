package com.css.bdpfnew.service;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfLog;

/**
* @Author HEYCH 
* @Date 2018年12月19日
* @Description:
*/
public interface CdpfLogService extends BaseService<CdpfLog, Long> {

	@Transactional
	boolean create(String cdpfId, String requestId, String logData, Integer logType, Integer opertype);
}