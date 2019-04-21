package com.css.bdpfnew.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoNameEditParams;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfNameChangeLog;
import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.CdpfNameChangeLogRepository;
import com.css.bdpfnew.repository.CitizenNetRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.HistoryRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.NameEditService;
import com.css.bdpfnew.util.UuidUtil;

/**
 * @Author HEYCH
 * @Date 2019年1月27日
 * @Description:
 */

@Service
public class NameEditServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements NameEditService {
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private CitizenNetRepository citizenNetRepository;
	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CdpfNameChangeLogRepository nameChangeLogRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private CdpfLogService cdpfLogService;
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	public void setBaseDao(CitizenRepository citizenRepository) {
		super.setBaseDao(citizenRepository);
	}

	@Override
	@Transactional
	public String nameEdit(DtoNameEditParams dtoNameEdit) {
		String newName = dtoNameEdit.getNewname().trim();
		CdpfCitizen citizen = citizenRepository.findByUuid(dtoNameEdit.getCdpfId());
		String cdpfId = "";
		if(citizen != null) {
			String oldName = citizen.getName();
			cdpfId = citizen.getUuid();
			
			//1 更新CdpfCitizen
			citizen.setOldname(oldName);
			citizen.setName(newName);
			citizen.setHlSynced(new Integer(9)); //同步到互联网用到
			citizen.setWaitforcardchange(new Integer(1)); //待卡残损换新标记
			citizenRepository.save(citizen);
			
			//7 更新CdpfCitizenNet
			CdpfCitizenNet citizenNet = citizenNetRepository.findByCitizenId(citizen.getCitizenId());
			if (citizenNet != null) {
				citizenNet.setName(newName);
				citizenNetRepository.save(citizenNet);
		    }
			
			//2 更新CdpfCard
			if(citizen.getCitizenId() != null && citizen.getCitizenId() != ""){
				List<CdpfCard> cards = cdpfCardRepository.findByCitizenId(citizen.getCitizenId());
				if(cards.size() > 0) {
					for(CdpfCard card: cards){
						card.setName(newName);
						cdpfCardRepository.save(card);
					} 
				}
			}
			
			//3 更新Request   用此方式是为了去掉默认的del_flag = 0
			if(citizen.getCitizenId() != null && citizen.getCitizenId() != ""){
				String sqlstr = "update request r set r.citizen_name = '" + newName + "' where r.citizen_id = '" + citizen.getCitizenId() + "'" ;
				Query nativeQuery = entityManager.createNativeQuery(sqlstr);
		        nativeQuery.executeUpdate();
			}
			
			//4 更新History
			if(citizen.getCitizenId() != null && citizen.getCitizenId() != ""){
				List<History> historys = historyRepository.findByCitizenId(citizen.getCitizenId());
				if(historys.size() > 0) {
					for(History history: historys){
						history.setCitizenName(newName);
						historyRepository.save(history);
					} 
				}
			}
			
			//5 专门记录姓名修改的表 CdpfNameChangeLog
			SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
			CdpfNameChangeLog namechangeLog = new CdpfNameChangeLog();
			namechangeLog.setUuid(UuidUtil.getUuid());
			namechangeLog.setCdpfId(citizen.getUuid());
			namechangeLog.setOldname(oldName);
			namechangeLog.setNewname(newName);
			namechangeLog.setOperid(user.getUuid());
			namechangeLog.setOpername(user.getNickname());
			namechangeLog.setEdittime(Calendar.getInstance().getTime());
			nameChangeLogRepository.save(namechangeLog);
			
			//6 操作日志 cdpfLog
			String cdpfIdStr = citizen.getUuid(); 
			String requestIdStr = citizen.getUuid();  //不涉及流程，临时设置成cdpfId
			String logData = "由 [" + oldName + "] 修改为 [" + newName + "]";
			Integer logType = new Integer(801);
			Integer opertype = new Integer(5);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			
		}
		return cdpfId;
	}
}
