/**
 * 
 */
package com.css.bdpfnew.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoCdpfZhuxiao;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfZhuxiao;
import com.css.bdpfnew.repository.CdpfZhuxiaoRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.ZhuxiaoService;

/**
 * @Author erDuo
 * @Date 2018年12月7日 下午5:51:16
 * @Description
 */

@Service
public class ZhuxiaoServiceImpl extends BaseServiceImpl<CdpfZhuxiao, Long> implements ZhuxiaoService {

	@Autowired
	private CdpfZhuxiaoRepository cdpfZhuxiaoRepository;

	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	public void setBaseDao(CdpfZhuxiaoRepository cdpfZhuxiaoRepository) {
		super.setBaseDao(cdpfZhuxiaoRepository);
	}

	@Override
	public String noLogout(DtoCdpfZhuxiao dtoCdpfZhuxiao) {

		CdpfZhuxiao zhuxiao = cdpfZhuxiaoRepository.findByCitizenId(dtoCdpfZhuxiao.getCitizenId());

		zhuxiao.setStatus(dtoCdpfZhuxiao.getStatus());

		cdpfZhuxiaoRepository.save(zhuxiao);
		
		//操作日志
		String cdpfIdStr = dtoCdpfZhuxiao.getCdpfId(); 
		String requestIdStr = dtoCdpfZhuxiao.getCdpfId();  //不涉及流程，临时设置成cdpfId
		String logData = "待注销数据操作：不注销";
		Integer logType = new Integer(930);
		Integer opertype = new Integer(1);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
		
		return zhuxiao.getUuid();
	}

	@Override
	public String unlock(DtoCdpfZhuxiao dtoCdpfZhuxiao) {

		CdpfCitizen citizen = citizenRepository.findByUuid(dtoCdpfZhuxiao.getCdpfId());
		if (citizen == null) {
			return null;
		}
		citizen.setStatusRecord(citizen.getStatusRecord() - 80);
		citizen.setHadFinalReviewed(citizen.getHadFinalReviewed() - 80);

		citizenRepository.save(citizen);

		//解冻操作日志
		String cdpfIdStr = dtoCdpfZhuxiao.getCdpfId(); 
		String requestIdStr = dtoCdpfZhuxiao.getCdpfId();  //不涉及流程，临时设置成cdpfId
		String logData = "待注销数据操作：解冻";
		Integer logType = new Integer(936);
		Integer opertype = new Integer(1);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

		return citizen.getUuid();
	}

	@Override
	public String lock(DtoCdpfZhuxiao dtoCdpfZhuxiao) {

		CdpfCitizen citizen = citizenRepository.findByUuid(dtoCdpfZhuxiao.getCdpfId());
		if (citizen == null) {
			return null;
		}
		citizen.setStatusRecord(citizen.getStatusRecord() + 80);
		citizen.setHadFinalReviewed(citizen.getHadFinalReviewed() + 80);
		citizen.setDongjietime(Calendar.getInstance().getTime());

		citizenRepository.save(citizen);

		//冻结操作日志
		String cdpfIdStr = dtoCdpfZhuxiao.getCdpfId(); 
		String requestIdStr = dtoCdpfZhuxiao.getCdpfId();  //不涉及流程，临时设置成cdpfId
		String logData = "待注销数据操作：冻结";
		Integer logType = new Integer(935);
		Integer opertype = new Integer(1);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

		return citizen.getUuid();
	}

}