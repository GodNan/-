package com.css.bdpfnew.service.impl.card;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCardLog;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.card.CdpfCardLogRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.card.CardGiveService;

/**
 * @Author HEYCH
 * @Date 2018年10月28日
 * @Description:
 */
@Service
public class CardGiveServiceImpl implements CardGiveService {

	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private CdpfCardLogRepository cdpfCardLogRepository;
	@Autowired
	private CdpfLogService cdpfLogService;

//	private String newCardNo;   //新卡号
//	private String cardNoFlagT;  //卡标记位(cardNoFlagT是为了避免和citizen的成员变量相同)
//	private Integer check260; //add at 2016.04.12 卡延期失败260处理字段

	@Override
	@Transactional
	public boolean update(String requestIdCard, Integer check260, String cardNoFlagT, String newCardNo)
			throws ParseException {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		
		CdpfCard card = cdpfCardRepository.findByRequestId(requestIdCard);

		CdpfCitizen citizen = citizenRepository.findByUuid(card.getCdpfId());
		citizen.setCardHlSynced(new Integer(0));
		citizen.setCardStatus(new Integer(340));
		card.setCardStatus(new Integer(340)); // 发卡完成
		// 有效期
		Calendar curr = Calendar.getInstance();
		curr.add(Calendar.DATE, 365); // 365为卡延期天数
		Date date = curr.getTime();

		if (check260 != null && (check260 == 260 || check260 == 6006)) {
			// card.setCardStatus(new Integer(340)); // 发卡完成
			card.setGivecarddate(Calendar.getInstance().getTime()); // 发卡时间
			card.setCardnoflag(cardNoFlagT); // 卡标记位
			card.setCardNo(newCardNo); // 卡号
			card.setCarddeadline(date); // 有效期

			//操作日志   处理卡延期失败260错误的 日志(首次发卡失败，卡延期重新发卡)
			String cdpfIdStr = card.getCdpfId(); 
			String requestIdStr = card.getRequestId();
			String logData = "一卡通号："+newCardNo;
			Integer logType = new Integer(check260);
			Integer opertype = new Integer(5);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
			
		} else {
			// card.setCardStatus(new Integer(340)); // 发卡完成
			// TODO: 互联网待同步标志
			card.setGivecarddate(Calendar.getInstance().getTime()); // 发卡时间

			if (card.getCardBusiness() == 110 || card.getCardBusiness() == 111 || card.getCardBusiness() == 1109) { // 新办发卡
				citizen.setHadGaveCard(new Integer(1));
				card.setCardnoflag(cardNoFlagT); // 卡标记位
				card.setCardNo(newCardNo); // 卡号
				card.setCarddeadline(date); // 有效期
				card.setFirstgivecarddate(Calendar.getInstance().getTime()); // 首次发卡时间
				citizen.setFirstgivecarddate(Calendar.getInstance().getTime());// 首次发卡时间

			} else if (card.getCardBusiness() == 120 || card.getCardBusiness() == 1209) { // 补办发卡
				card.setCardnoflag(cardNoFlagT);
				card.setCardNo(newCardNo);
				card.setCarddeadline(date);

			} else if (card.getCardBusiness() == 140 || card.getCardBusiness() == 1409) { // 残损发卡

				card.setCardnoflag(cardNoFlagT);
				card.setCardNo(newCardNo);
				card.setCarddeadline(date);

			}
		}
		// card.setCardEdittime(Calendar.getInstance().getTime()); 是否有卡编辑时间
		// card.setUpdateFlag(new Integer(1));
		citizenRepository.save(citizen);
		cdpfCardRepository.save(card);
		
		//卡操作日志
		CdpfCardLog cardLog = new CdpfCardLog();
		cardLog.setRequestId(card.getRequestId());
		cardLog.setCdpfId(citizen.getUuid());
		cardLog.setCardStatus(card.getCardStatus());
		cardLog.setCitizenId(citizen.getCitizenId());
		cardLog.setPcno(card.getPcno());
		cardLog.setLogdata("发卡完成");
		cardLog.setOpername(user.getNickname());
		cdpfCardLogRepository.save(cardLog);
		
		//操作日志
		String cdpfIdStr = card.getCdpfId(); 
		String requestIdStr = card.getRequestId();
		String logData = "一卡通号："+newCardNo;
		Integer logType = new Integer(115);
		Integer opertype = new Integer(5);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

		return true;
	}
}
