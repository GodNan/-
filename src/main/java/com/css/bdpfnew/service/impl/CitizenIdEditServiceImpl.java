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
import org.springframework.util.StringUtils;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoCitizenIdEditParams;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfDangAn;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfIdChangeLog;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfReviewAll;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfZhuxiao;
import com.css.bdpfnew.model.entity.bdpfnew.GonganData;
import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.CdpfCitizenIdChangeLogRepository;
import com.css.bdpfnew.repository.CdpfPhotoRepository;
import com.css.bdpfnew.repository.CdpfReviewAllRepository;
import com.css.bdpfnew.repository.CdpfZhuxiaoRepository;
import com.css.bdpfnew.repository.CitizenGuardianPhotosRepository;
import com.css.bdpfnew.repository.CitizenNetRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.DangAnRepository;
import com.css.bdpfnew.repository.GonganDataRepository;
import com.css.bdpfnew.repository.HistoryRepository;
import com.css.bdpfnew.repository.LogoutPhotoRepository;
import com.css.bdpfnew.repository.UnlockPhotoRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.repository.idt.IdtPhotoRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.CitizenIdEditService;
import com.css.bdpfnew.util.UuidUtil;

/**
 * @Author HEYCH
 * @Date 2019年2月26日
 * @Description:
 */

@Service
public class CitizenIdEditServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements CitizenIdEditService {
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CdpfCitizenIdChangeLogRepository idChangeLogRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private CdpfPhotoRepository cdpfPhotoRepository;
	@Autowired
	private IdtPhotoRepository idtPhotoRepository;
	@Autowired
	private CitizenGuardianPhotosRepository citizenGuardianPhotosRepository;
	@Autowired
	private LogoutPhotoRepository citizenLogoutPhotosRepository;
	@Autowired
	private UnlockPhotoRepository unlockPhotoRepository;
	@Autowired
	private CitizenNetRepository citizenNetRepository;
	@Autowired
	private CdpfReviewAllRepository cdpfReviewAllRepository;
	@Autowired
	private CdpfZhuxiaoRepository cdpfZhuxiaoRepository;
	@Autowired
	private DangAnRepository dangAnRepository;
	@Autowired
	private GonganDataRepository gonganDataRepository;
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
	public String citizenIdEdit(DtoCitizenIdEditParams dtoCitizenIdEdit) {
		String newCitizenId = dtoCitizenIdEdit.getNewCitizenId().trim().toUpperCase();
		CdpfCitizen citizen = citizenRepository.findByUuid(dtoCitizenIdEdit.getCdpfId());
		String cdpfId = "";
		if(citizen != null) {
			String oldCitizenId = citizen.getCitizenId();
			String cardNum = citizen.getCardNum().replaceAll(oldCitizenId, newCitizenId);
			cdpfId = citizen.getUuid();
			
			if(!StringUtils.isEmpty(oldCitizenId) && !StringUtils.isEmpty(newCitizenId)) {
				//1 更新CdpfCitizen
				citizen.setOldCitizenId(oldCitizenId);
				citizen.setCitizenId(newCitizenId);
				citizen.setCardNum(cardNum);
				citizen.setBirthdate(dtoCitizenIdEdit.getNewBirthdate());
				citizen.setHlSynced(new Integer(9)); //同步到互联网用到
				citizen.setWaitforcardchange(new Integer(1)); //待卡残损换新标记
				citizenRepository.save(citizen);
				
				//2 更新CdpfCard
				List<CdpfCard> cards = cdpfCardRepository.findByCitizenId(oldCitizenId);
				if(cards.size() > 0) {
					for(CdpfCard card: cards){
						card.setCitizenId(newCitizenId);
						cdpfCardRepository.save(card);
					} 
				}
				
				//3 更新Request   用此方式是为了去掉默认的del_flag = 0
				String sqlstr = "update request r set r.citizen_id = '" + newCitizenId + "' where r.citizen_id = '" + oldCitizenId + "'" ;
				Query nativeQuery = entityManager.createNativeQuery(sqlstr);
			    nativeQuery.executeUpdate();
				
				//4 更新History
			    List<History> historys = historyRepository.findByCitizenId(oldCitizenId);
				if(historys.size() > 0) {
					for(History history: historys){
						history.setCitizenId(newCitizenId);
						historyRepository.save(history);
					} 
				}
				
				//5 更新CdpfPhoto
			    /*CdpfPhoto cdpfPhoto = cdpfPhotoRepository.findByCitizenId(oldCitizenId);
			    if (cdpfPhoto != null) {
			    	cdpfPhoto.setCitizenId(newCitizenId);
				    cdpfPhotoRepository.save(cdpfPhoto);
			    }*/
			    cdpfPhotoRepository.updateByCitizenId(oldCitizenId, newCitizenId);
			    
				//6 更新CitizenIdtPhoto
			    /*List<CitizenIdtPhoto> citizenIdtPhotos = idtPhotoRepository.findByCitizenId(oldCitizenId);
				if(citizenIdtPhotos.size() > 0) {
					for(CitizenIdtPhoto idtPhoto: citizenIdtPhotos){
						idtPhoto.setCitizenId(newCitizenId);
						idtPhotoRepository.save(idtPhoto);
					} 
				}*/
			    idtPhotoRepository.updateByCitizenId(oldCitizenId, newCitizenId);
				
				//7 更新CitizenGuardianPhotos
			    /*List<CitizenGuardianPhotos> guardianPhotos = citizenGuardianPhotosRepository.findByCdpfid(citizen.getUuid());
				if(guardianPhotos.size() > 0) {
					for(CitizenGuardianPhotos guardianPhoto: guardianPhotos){
						guardianPhoto.setCitizenId(newCitizenId);
						citizenGuardianPhotosRepository.save(guardianPhoto);
					} 
				}*/
			    citizenGuardianPhotosRepository.updateByCdpfid(citizen.getUuid(), newCitizenId);
				
				//8 更新CitizenLogoutPhotos
				/*CitizenLogoutPhotos logoutPhoto = citizenLogoutPhotosRepository.findByCdpfId(citizen.getUuid());
				if (logoutPhoto != null) {
					logoutPhoto.setCitizenId(newCitizenId);
					citizenLogoutPhotosRepository.save(logoutPhoto);
			    }*/
			    citizenLogoutPhotosRepository.updateByCdpfId(citizen.getUuid(), newCitizenId);
				
				//9 更新CitizenJiedongPhoto
				/*CitizenJiedongPhoto jiedongPhoto = unlockPhotoRepository.findByCdpfId(citizen.getUuid());
				if (jiedongPhoto != null) {
					jiedongPhoto.setCitizenId(newCitizenId);
					unlockPhotoRepository.save(jiedongPhoto);
			    }*/
			    unlockPhotoRepository.updateByCdpfId(citizen.getUuid(), newCitizenId);
				
				//10 更新CdpfMiddleArea
				String sqlstr2 = "update cdpf_middle_area r set r.personal_id = '" + newCitizenId + "' where r.personal_id = '" + oldCitizenId + "'" ;
				Query nativeQuery2 = entityManager.createNativeQuery(sqlstr2);
				nativeQuery2.executeUpdate();
				
				//11 更新CdpfCitizenNet
				CdpfCitizenNet citizenNet = citizenNetRepository.findByCitizenId(oldCitizenId);
				if (citizenNet != null) {
					citizenNet.setCitizenId(newCitizenId);
					citizenNetRepository.save(citizenNet);
			    }
				
				//12 更新CdpfReviewAll
			    List<CdpfReviewAll> cdpfReviewAlls = cdpfReviewAllRepository.findByCitizenId(oldCitizenId);
				if(cdpfReviewAlls.size() > 0) {
					for(CdpfReviewAll cdpfReviewAll: cdpfReviewAlls){
						cdpfReviewAll.setCitizenId(newCitizenId);
						cdpfReviewAllRepository.save(cdpfReviewAll);
					} 
				}
				
				//13 更新btgl_card_status
				String sqlstr3 = "update btgl_card_status r set r.citizen_id = '" + newCitizenId + "' where r.citizen_id = '" + oldCitizenId + "'" ;
				Query nativeQuery3 = entityManager.createNativeQuery(sqlstr3);
			    nativeQuery3.executeUpdate();
			    
			    //14 更新btgl_bank
				String sqlstr4 = "update btgl_bank r set r.citizen_id = '" + newCitizenId + "' where r.citizen_id = '" + oldCitizenId + "'" ;
				Query nativeQuery4 = entityManager.createNativeQuery(sqlstr4);
			    nativeQuery4.executeUpdate();
			    
			    //15 更新CdpfZhuxiao
			    CdpfZhuxiao cdpfZhuxiao = cdpfZhuxiaoRepository.findByCitizenId(oldCitizenId);
				if (cdpfZhuxiao != null) {
					cdpfZhuxiao.setCitizenId(newCitizenId);
					cdpfZhuxiaoRepository.save(cdpfZhuxiao);
			    }
				
				//16 更新CdpfDangAn
				CdpfDangAn cdpfDangAn = dangAnRepository.findByCitizenId(oldCitizenId);
				if (cdpfDangAn != null) {
					cdpfDangAn.setCitizenId(newCitizenId);
					dangAnRepository.save(cdpfDangAn);
			    }
				
				//17 更新GonganData
				GonganData gonganData = gonganDataRepository.findByCitizenId(oldCitizenId);
				if (gonganData != null) {
					gonganData.setCitizenId(newCitizenId);
					gonganDataRepository.save(gonganData);
			    }
				
				//18 专门记录身份证号修改的表 CdpfidChangeLog
				SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
				CdpfIdChangeLog idChangeLog = new CdpfIdChangeLog();
				idChangeLog.setUuid(UuidUtil.getUuid());
				idChangeLog.setCdpfId(citizen.getUuid());
				idChangeLog.setOldcitizenid(oldCitizenId);
				idChangeLog.setNewcitizenid(newCitizenId);
				idChangeLog.setOperid(user.getUuid());
				idChangeLog.setOpername(user.getNickname());
				idChangeLog.setEdittime(Calendar.getInstance().getTime());
				idChangeLogRepository.save(idChangeLog);
				
				//19 操作日志 cdpfLog
				String cdpfIdStr = citizen.getUuid(); 
				String requestIdStr = citizen.getUuid();  //不涉及流程，临时设置成cdpfId
				String logData = "由 [" + oldCitizenId + "] 修改为 [" + newCitizenId + "]";
				Integer logType = new Integer(802);
				Integer opertype = new Integer(5);
				cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			}
			
		}
		return cdpfId;
	}
}
