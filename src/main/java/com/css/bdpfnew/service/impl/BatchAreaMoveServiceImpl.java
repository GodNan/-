package com.css.bdpfnew.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoBatchAreaAccept;
import com.css.bdpfnew.model.dto.DtoBatchAreaMove;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfMiddleArea;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.repository.CdpfMiddleAreaRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.service.BatchAreaMoveService;
import com.css.bdpfnew.service.CdpfLogService;

/**
 * @Author HEYCH
 * @Date 2018年12月12日 上午9:42:27
 * @Description:
 */
@Service
public class BatchAreaMoveServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements BatchAreaMoveService {

	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private CdpfMiddleAreaRepository cdpfMiddleAreaRepository;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private CdpfLogService cdpfLogService;

	@Autowired
	public BatchAreaMoveServiceImpl(CitizenRepository citizenRepository) {
		this.citizenRepository = citizenRepository;
	}

	@Override
	@Transactional
	public void areaBatchMoveApply(DtoBatchAreaMove dtoBatchAreaMove) {
		for(String citizenId:dtoBatchAreaMove.getCitizenIds()){
			List<CdpfMiddleArea> ls = cdpfMiddleAreaRepository.findByPersonalId(citizenId);
			if(ls.size() != 0){
				System.out.println("残疾人为待接收状态，无法再次转移！");
				return;
			}
			
			CdpfCitizen citizen = citizenRepository.findByCitizenId(citizenId);
			citizen.setIsMoving(new Integer(2));  //批量迁移设置为2
			citizenRepository.save(citizen);
			
			String preName = getHjd(citizen.getCityid());
			String nextName = getHjd(dtoBatchAreaMove.getNewCityid());
			
			CdpfMiddleArea cma = new CdpfMiddleArea();
			cma.setCdpfId(citizen.getUuid());//主表UUID
			cma.setPersonalId(citizen.getCitizenId());//主表身份证号
			cma.setPname(citizen.getName());
			cma.setPreArea(citizen.getCityid());
			cma.setNextArea(dtoBatchAreaMove.getNewCityid());
			cma.setPreName(preName);
			cma.setNextName(nextName);
			cma.setStatus(new Integer(0)); 
			cma.setPreTime(Calendar.getInstance().getTime());//迁出方申请时间
			cma.setIsHulian(new Integer(0));//政务网设置成0
			cma.setHlSynced(new Integer(0));//政务网设置成0
			cma.setUpdateFlag(1);
			cdpfMiddleAreaRepository.save(cma);
			
			//操作日志
			String cdpfIdStr = citizen.getUuid(); 
			String requestIdStr = citizen.getRequestId();
			String logData = "残疾人批量迁出";
			Integer logType = new Integer(601);
			Integer opertype = new Integer(5);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
		}
	}

	@Override
	@Transactional
	public void areaBatchMoveAccept(DtoBatchAreaAccept dtoBatchAreaAccept) {
		
		for(String cdpfId :dtoBatchAreaAccept.getCdpfIds()){
			
			CdpfMiddleArea cma = cdpfMiddleAreaRepository.findByCdpfId(cdpfId);
			if(cma != null) {
				cma.setStatus(new Integer(1));  //接收
				cma.setNextTime(Calendar.getInstance().getTime());//接收时间
				cdpfMiddleAreaRepository.save(cma);
			}
			
			CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
			if(citizen != null) {
				citizen.setIsMoving(new Integer(0));
				citizen.setCityid(cma.getNextArea());
				citizenRepository.save(citizen);
			}
			
			//操作日志
			String cdpfIdStr = citizen.getUuid(); 
			String requestId = citizen.getRequestId();
			String logData = "残疾人批量迁入操作：接收";
			Integer logType = new Integer(602);
			Integer opertype = new Integer(5);
			cdpfLogService.create(cdpfIdStr, requestId, logData, logType, opertype);
		}
	}
	
	@Override
	@Transactional
	public void areaBatchMoveNotAccept(DtoBatchAreaAccept dtoBatchAreaAccept) {
		
		for(String cdpfId :dtoBatchAreaAccept.getCdpfIds()){
			
			CdpfMiddleArea cma = cdpfMiddleAreaRepository.findByCdpfId(cdpfId);
			if(cma != null) {
				cma.setStatus(new Integer(2)); //不接收
				cma.setNextTime(Calendar.getInstance().getTime());//不接收时间
				cdpfMiddleAreaRepository.save(cma);
			}
			
			CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);
			if(citizen != null) {
				citizen.setIsMoving(new Integer(0));
				citizenRepository.save(citizen);
			}
			//操作日志
			String cdpfIdStr = citizen.getUuid(); 
			String requestId = citizen.getRequestId();
			String logData = "残疾人批量迁入操作：不接收";
			Integer logType = new Integer(603);
			Integer opertype = new Integer(5);
			cdpfLogService.create(cdpfIdStr, requestId, logData, logType, opertype);
		}
	}
	
	public String getHjd(String cityid){
		String hjd = "";
		if(cityid.length() == 6){
			hjd +=  areaRepository.findByAreaCode(cityid).getAreaName();
		}else if(cityid.length() == 9){
			hjd +=  areaRepository.findByAreaCode(cityid.substring(0, 6)).getAreaName();
			hjd +=  areaRepository.findByAreaCode(cityid).getAreaName();
		}else if(cityid.length() == 12){
			hjd +=  areaRepository.findByAreaCode(cityid.substring(0, 6)).getAreaName();
			hjd +=  areaRepository.findByAreaCode(cityid.substring(0, 9)).getAreaName();
			hjd +=  areaRepository.findByAreaCode(cityid).getAreaName();
		}
		return hjd;
	}

}
